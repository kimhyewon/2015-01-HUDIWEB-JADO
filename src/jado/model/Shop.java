package jado.model;

public class Shop {
	private String url;
	private String title;
	private String phone;
	private String banner_url;
	private String main_url;
	private String logo_url;
	private String footer;
	private String theme;

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

	@Override
	public String toString() {
		return "Shop [url=" + url + ", title=" + title + ", phone=" + phone + ", banner_url=" + banner_url + ", main_url=" + main_url + ", logo_url=" + logo_url + ", theme=" + theme + ", footer="
				+ footer + "]";
	}
	public boolean updateFromUserPage(Shop shopFromEdit) {
		boolean result = false;
		if (!this.phone.equals(shopFromEdit.phone)) {
			this.phone = shopFromEdit.phone;
			result = true;
		}
		return result;
	}

	
}
