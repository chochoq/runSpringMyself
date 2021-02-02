package database;

public class SqlVO {
	   private String table; //테이블이름
	   private String key; //
	   private String word; //
	   private int page; //현재페이지
	   private int perPage; //전체페이지
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	@Override
	public String toString() {
		return "SqlVO [table=" + table + ", key=" + key + ", word=" + word + ", page=" + page + ", perPage=" + perPage
				+ "]";
	}
	   
	   
	
}