package AdServer.AdServerCampaignDemo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public static class Campaign {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
		
		private long partnerID;
		private long duration;
		private String adContent;
		private LocalDateTime creationDTime;
		
		public Campaign(long partnerID, long duration, String adContent) {
			super();
			this.partnerID = partnerID;
			this.duration = duration;
			this.adContent = adContent;
			this.creationDTime = LocalDateTime.now();
		}

		

		public long getPartnerID() {
			return partnerID;
		}

		public void setPartnerID(long partnerID) {
			this.partnerID = partnerID;
		}

		public long getDuration() {
			return duration;
		}

		public void setDuration(long duration) {
			this.duration = duration;
		}

		public String getAdContent() {
			return adContent;
		}

		public void setAdContent(String adContent) {
			this.adContent = adContent;
		}

		public LocalDateTime getCreationDTime() {
			return creationDTime;
		}

		public void setCreationDTime(LocalDateTime creationDTime) {
			this.creationDTime = creationDTime;
		}

		@Override
		public String toString() {
			return "campaign [id=" + id + ", partnerID=" + partnerID + ", duration=" + duration + ", adContent="
					+ adContent + ", creationDTime=" + creationDTime + "]";
		}
		

		public int compareTo(Campaign c) {
			return (this.id>c.id) ? -1 : (this.id<c.id) ? 1 : 0;
		}
		
		
		
		
}
