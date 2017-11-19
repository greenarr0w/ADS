package ch.zhaw.ads.praktikum9;

class Town {
	int hashCode;
	String name;
	String nb;
	Town (int hashCode, String name, String nb) {
		this.name = name; this.hashCode = hashCode; this.nb = nb;
	}

	@Override
	public boolean equals(Object o) {
		return o.equals(this.name);
	}

	@Override
	public int hashCode() {
		return hashCode;
	}
}
