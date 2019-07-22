package bean;

public class Page {
	    private int pageNo;
	    private int pageSize;
	    private int totalRows;
	    private int totalPages;
	    private int startIndex;
		public Page() {
			
		}
		
		public Page(int pageNo, int pageSize, int totalRows, int totalPages, int startIndex) {
			
			this.pageNo = pageNo;
			this.pageSize = pageSize;
			this.totalRows = totalRows;
			this.totalPages = totalPages;
			this.startIndex = startIndex;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalRows() {
			return totalRows;
		}
		public void setTotalRows(int totalRows) {
			this.totalRows = totalRows;
		}
		public int getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
		public int getStartIndex() {
			return startIndex;
		}
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}
	    
}
