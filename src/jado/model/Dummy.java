package jado.model;

public class Dummy {
	private Integer no;
	private String name;

	public Dummy() {
	}
	
	public Dummy(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dummy [no=" + no + ", name=" + name + "]";
	}
}
