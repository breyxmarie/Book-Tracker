package net.codejava.ws;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Book {

	// private int id;
	private int id;
	private String Title;
	private String Author;
	private String PubYear;
	private String Genre;
	private String Plot;
	private String Status;
	private byte[] Cover;
	private String ratings;
	private String comment;
	private String StartDate;
	private String EndDate;
	private Date SDate;

	public Book(String Title, String Author, String PubYear, String Genre, String Plot, String Status) {

		this.Title = Title;
		this.Author = Author;
		this.PubYear = PubYear;
		this.Genre = Genre;
		this.Plot = Plot;
		this.Status = Status;
	}

	public Book(String Title, String Author, String PubYear, String Genre, String Plot, String Status, byte[] Cover) {

		this.Title = Title;
		this.Author = Author;
		this.PubYear = PubYear;
		this.Genre = Genre;
		this.Plot = Plot;
		this.Status = Status;
		this.Cover = Cover;
	}

	public Book(int id, String Title, String Author, String PubYear, String Genre, String Plot, String Status) {
		this.id = id;
		this.Title = Title;
		this.Author = Author;
		this.PubYear = PubYear;
		this.Genre = Genre;
		this.Plot = Plot;
		this.Status = Status;
	}

	public Book(int id, String Title, String Author, String PubYear, String Genre, String Plot) {
		this.id = id;
		this.Title = Title;
		this.Author = Author;
		this.PubYear = PubYear;
		this.Genre = Genre;
		this.Plot = Plot;

	}

	public Book(int id, String Title, String Author, String PubYear, String Genre, String Plot, byte[] Cover) {
		this.id = id;
		this.Title = Title;
		this.Author = Author;
		this.PubYear = PubYear;
		this.Genre = Genre;
		this.Plot = Plot;
		this.Cover = Cover;
	}

	public Book() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + Title + ", Author= " + Author + ", PubYear=" + PubYear + ", Genre="
				+ Genre + ", Plot=" + Plot + ", Status=" + Status + "]";
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getCover() {
		return Cover;
	}

	public void setCover(byte[] cover) {
		Cover = cover;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPubYear() {
		return PubYear;
	}

	public void setPubYear(String pubYear) {
		PubYear = pubYear;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

}
