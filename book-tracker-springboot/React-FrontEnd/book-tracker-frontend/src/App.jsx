import { useState, useEffect } from "react";
import "./App.scss";

function App() {
  const [books, setBooks] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [genre, setGenre] = useState("");
  const [status, setStatus] = useState("WISHLIST");
  const [rating, setRating] = useState(1);
  const [notes, setNotes] = useState("");

  const fetchBooks = () => {
    fetch("http://localhost:8080/api/books")
      .then((response) => response.json())
      .then((data) => setBooks(data))
      .catch((error) => console.error("Error fetching books:", error));
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const startEdit = (book) => {
    setEditingId(book.id);
    setTitle(book.title);
    setAuthor(book.author);
    setGenre(book.genre);
    setStatus(book.status);
    setRating(book.rating);
    setNotes(book.notes);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const bookData = {
      title,
      author,
      genre,
      status,
      rating: Number(rating),
      notes,
    };

    const url = editingId
      ? `http://localhost:8080/api/books/${editingId}`
      : "http://localhost:8080/api/books";

    const method = editingId ? "PUT" : "POST";

    fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(bookData),
    })
      .then((response) => response.json())
      .then(() => {
        fetchBooks();
        setEditingId(null);
        setTitle("");
        setAuthor("");
        setGenre("");
        setStatus("WISHLIST");
        setRating(1);
        setNotes("");
      })
      .catch((error) => console.error("Error saving book:", error));
  };

  const handleDelete = (id) => {
    fetch(`http://localhost:8080/api/books/${id}`, {
      method: "DELETE",
    })
      .then(() => fetchBooks())
      .catch((error) => console.error("Error deleting book:", error));
  };

  return (
    <div className="library">
      <h1 className="library__title">My Book Library</h1>

      <form className="book-form" onSubmit={handleSubmit}>
        <input
          className="book-form__input"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <input
          className="book-form__input"
          placeholder="Author"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
          required
        />
        <input
          className="book-form__input"
          placeholder="Genre"
          value={genre}
          onChange={(e) => setGenre(e.target.value)}
        />
        <select
          className="book-form__select"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="WISHLIST">Wishlist</option>
          <option value="READING">Reading</option>
          <option value="COMPLETED">Completed</option>
        </select>
        <input
          className="book-form__input book-form__input--rating"
          type="number"
          min="1"
          max="5"
          value={rating}
          onChange={(e) => setRating(e.target.value)}
        />
        <input
          className="book-form__input"
          placeholder="Notes"
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
        />
        <button className="book-form__submit" type="submit">
          {editingId ? "Update Book" : "Add Book"}
        </button>
      </form>

      <ul className="book-list">
        {books.map((book) => (
          <li className="book-card" key={book.id}>
            <span className="book-card__info">
              <span className="book-card__title">{book.title}</span>
              <span className="book-card__author"> by {book.author}</span>
              <span className={`book-card__status book-card__status--${book.status.toLowerCase()}`}>
                {book.status}
              </span>
              <span className="book-card__rating">{book.rating}/5</span>
            </span>
            <span className="book-card__actions">
              <button className="book-card__edit" onClick={() => startEdit(book)}>
                Edit
              </button>
              <button className="book-card__delete" onClick={() => handleDelete(book.id)}>
                Delete
              </button>
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;