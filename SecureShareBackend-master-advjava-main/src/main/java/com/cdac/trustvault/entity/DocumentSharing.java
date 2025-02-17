package com.cdac.trustvault.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "document_sharing")
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class DocumentSharing {

	     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long documentId;  // Store document ID as a simple reference

	    private String sharedBy;
	    private String sharedWith;
	    private LocalDateTime sharedAt;
	    private String documentName;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getDocumentId() {
			return documentId;
		}
		public void setDocumentId(Long documentId) {
			this.documentId = documentId;
		}
		public String getSharedBy() {
			return sharedBy;
		}
		public void setSharedBy(String sharedBy) {
			this.sharedBy = sharedBy;
		}
		public String getSharedWith() {
			return sharedWith;
		}
		public void setSharedWith(String sharedWith) {
			this.sharedWith = sharedWith;
		}
		public LocalDateTime getSharedAt() {
			return sharedAt;
		}
		public void setSharedAt(LocalDateTime sharedAt) {
			this.sharedAt = sharedAt;
		}
		public String getDocumentName() {
			return documentName;
		}
		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}
		@Override
		public String toString() {
			return "DocumentSharing [id=" + id + ", documentId=" + documentId + ", sharedBy=" + sharedBy
					+ ", sharedWith=" + sharedWith + ", sharedAt=" + sharedAt + ", documentName=" + documentName + "]";
		}
		
	    
	    
	    
	    
    
}