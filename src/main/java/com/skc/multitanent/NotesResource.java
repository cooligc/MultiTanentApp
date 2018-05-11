/**
 * 
 */
package com.skc.multitanent;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */
@RestController
@RequestMapping("/notes")
public class NotesResource {

	@Autowired
	NoteRepository repository;
	
	@PostMapping
	public Note createNote(@RequestBody Note note) {
		note.setId(UUID.randomUUID().toString());
		repository.save(note);
		return note;
	}
	
	@DeleteMapping("/{id}")
	public String deleteNote(@PathVariable("id") String noteId) {
		repository.delete(noteId);
		return "Deleted";
	}
	
	@GetMapping
	public Map<String, Note> getAllNote() {
		return repository.findAll();
	}
	
	
}


class Note extends MyRedisEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String description;
	private Date createdDate;
	private Date lastUpdated;
	private Date dueDate;
	
	private Note() {
		
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + ", createdDate=" + createdDate
				+ ", lastUpdated=" + lastUpdated + ", dueDate=" + dueDate + "]";
	}
	
	
	static class NoteBuilder {
		private static Note note;
		private NoteBuilder() {
			note = new Note();
		}
		
		public static NoteBuilder init() {
			return new NoteBuilder();
		}
		
		public static NoteBuilder init(Note note) {
			NoteBuilder.note = note;
			return new NoteBuilder();
		}
		
		public NoteBuilder withId(String id) {
			note.setId(id);
			return this;
		}
		
		public NoteBuilder withNoteTitle(String title) {
			note.setTitle(title);
			return this;
		}
		
		public NoteBuilder withDueDate(Date dueDate) {
			note.setDueDate(dueDate);
			return this;
		}
		
		public NoteBuilder withCreatedDate(Date createdDate) {
			note.setCreatedDate(createdDate);
			return this;
		}
		public NoteBuilder withLastUpdatedDate(Date lastUpdatedDate) {
			note.setLastUpdated(lastUpdatedDate);
			return this;
		}
		
		
		public Note build() {
			return note;
		}
	}
	
}

@Repository
class NoteRepository extends RedisCommonDao<Note>{
	
	private static final String KEY = "note";
	
	public NoteRepository() {
		setKey(KEY);
	}
	
	@Override
	public void save(Note redisEntity) {
		super.save(redisEntity);
	}
	
	@Override
	public void update(Note myRedisEntity) {
		super.update(myRedisEntity);
	}
	
	@Override
	public Map<String, Note> findAll() {
		return super.findAll();
	}
	
	@Override
	public Note findOne(String id) {
		return super.findOne(id);
	}
	
	
}