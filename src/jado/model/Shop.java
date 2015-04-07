package jado.model;

public class Shop {
	private String url;
	private String title;
	private String phone;
	private String banner_url;
	private String main_url;
	private String logo_url;
	private String theme;
	private String footer;

	
	public Shop(String url, String title, String phone, String banner_url, String main_url, String logo_url, String theme, String footer) {
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
		this(url, null, phone, null, null, null, null, null);
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
	@Override
	public String toString() {
		return "Shop [url=" + url + ", phone=" + phone + "]";
	}
	
	
}
