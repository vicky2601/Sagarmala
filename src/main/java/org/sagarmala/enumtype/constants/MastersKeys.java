package org.sagarmala.enumtype.constants;

public enum MastersKeys {
	STATE(0, "state"), AGENCY(1, "agency"), LINEMINISTRY(2, "lineministry"), DESIGNATION(3,
			"designation"), ORGANISATION(4, "organisation"), PORT(5, "port"), MARITIMEBOARD(6,
					"maritimeboard"), DEPARTMENT(7, "department"), MINORPORTS(8, "minnerPorts"), PROJECTS(9,
							"allProjects"), FUNDINGAGENCY(10, "fundingagency"), LOCATIONMASTER(11,
									"locationmaster"), ORGANIZATION(11, "organization"),DirectorateOfPorts(12,"directorateOfPorts");

	private int id;
	private String description;

	private MastersKeys(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
