package entity;

public class Info {
     private int id;
     private String province;
     private String Confirmed_num;
     private String Cured_num;
     private String Dead_num;
	
	

	public Info(int id, String province, String confirmed_num, String cured_num, String dead_num) {
		this.id = id;
		this.province = province;
		Confirmed_num = confirmed_num;
		Cured_num = cured_num;
		Dead_num = dead_num;
	}

	public Info(String province, String confirmed_num, String cured_num, String dead_num) {
		this.province = province;
		Confirmed_num = confirmed_num;
		Cured_num = cured_num;
		Dead_num = dead_num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}



	public String getConfirmed_num() {
		return Confirmed_num;
	}

	public void setConfirmed_num(String confirmed_num) {
		Confirmed_num = confirmed_num;
	}



	public String getCured_num() {
		return Cured_num;
	}

	public void setCured_num(String cured_num) {
		Cured_num = cured_num;
	}

	public String getDead_num() {
		return Dead_num;
	}

	public void setDead_num(String dead_num) {
		Dead_num = dead_num;
	}







	@Override
	public String toString() {
		return "Info [id=" + id + ", province=" + province + ", Confirmed_num=" + Confirmed_num + ", Cured_num="
				+ Cured_num + ", Dead_num=" + Dead_num + "]";
	}

	public Info() {
	}
     
}
