package com.julianomengue.classes;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "text")
public class Text {

	private String id;

	private String tag;

	private String name;

	private String article;

	public Text() {
		super();
	}

	public Text(String id, String tag, String name, String article) {
		super();
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.article = article;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
