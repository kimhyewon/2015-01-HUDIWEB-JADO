package jado.model;

import java.util.List;

public class Shop {
	private String url;
	private String title;
	private String phone;
	private String mainUrl;
	private String profileUrl;
	private String footer;
	private String theme;
	private String subImg1Url;
	private String subImg2Url;
	private String subImg3Url;

	private List<Board> boards;
	private List<Category> categorys;

	public Shop() {
	}

	public Shop(String url, String phone) {
		this.url = url;
		this.phone = phone;
		this.footer = "내용을 입력해 주세요";
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

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSubImg1Url() {
		return subImg1Url;
	}

	public void setSubImg1Url(String subImg1Url) {
		this.subImg1Url = subImg1Url;
	}

	public String getSubImg2Url() {
		return subImg2Url;
	}

	public void setSubImg2Url(String subImg2Url) {
		this.subImg2Url = subImg2Url;
	}

	public String getSubImg3Url() {
		return subImg3Url;
	}

	public void setSubImg3Url(String subImg3Url) {
		this.subImg3Url = subImg3Url;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	@Override
	public String toString() {
		return "Shop [url=" + url + ", title=" + title + ", phone=" + phone + ", mainUrl=" + mainUrl + ", profileUrl=" + profileUrl + ", footer=" + footer + ", theme=" + theme + ", subImg1Url="
				+ subImg1Url + ", subImg2Url=" + subImg2Url + ", subImg3Url=" + subImg3Url + ", boards=" + boards + ", categorys=" + categorys + "]";
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
