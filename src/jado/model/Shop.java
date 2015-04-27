package jado.model;

import java.util.List;

public class Shop {
	private String url;
	private String title;
	private String phone;
	private String banner_url;
	private String main_url;
	private String logo_url;
	private String footer;
	private String theme;
	private List<Board> boards;
	private List<Category> categorys;

	public Shop() {	}
	public Shop(String url, String title, String phone, String banner_url, String main_url, String logo_url, String footer, String theme) {
		super();
		this.url = url;
		this.title = title;
		this.phone = phone;
		this.banner_url = banner_url;
		this.main_url = main_url;
		this.logo_url = logo_url;
		this.theme = theme;
		this.footer = footer;
	}

	public Shop(String url, String phone) {
		this(url, null, phone, null, null, null, url, null);
	}
	

	public Shop(String url, String title, String phone, String footer) {
		super();
		this.url = url;
		this.title = title;
		this.phone = phone;
		this.footer = footer;
	}
	
	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getPhone() {
		return phone;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public String getMain_url() {
		return main_url;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public String getTheme() {
		return theme;
	}

	public String getFooter() {
		return footer;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}

	public void setMain_url(String main_url) {
		this.main_url = main_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
	public List<Board> getBoards() {
		return boards;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	@Override
	public String toString() {
		return "Shop [url=" + url + ", title=" + title + ", phone=" + phone + ", banner_url=" + banner_url + ", main_url=" + main_url + ", logo_url=" + logo_url + ", theme=" + theme + ", footer="
				+ footer + "]";
	}
	public boolean updateFromUserPage(Shop shopFromClient) {
		boolean result = false;
		
		if (!this.phone.equals(shopFromClient.phone)) {
			this.phone = shopFromClient.phone;
			result = true;
		}
		
		return result;
	}
	
	public boolean updateFromSettingPage(Shop shopFromClient) {
		boolean result = false;
		
		if (!this.title.equals(shopFromClient.title)) {
			this.title = shopFromClient.title;
			result = true;
		}
		if (!this.phone.equals(shopFromClient.phone)) {
			this.phone = shopFromClient.phone;
			result = true;
		}
		if (!this.footer.equals(shopFromClient.footer)) {
			this.footer = shopFromClient.footer;
			result = true;
		}
		return result;
	}

	
}
