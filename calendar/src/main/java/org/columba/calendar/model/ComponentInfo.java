// The contents of this file are subject to the Mozilla Public License Version
// 1.1
//(the "License"); you may not use this file except in compliance with the
//License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
//
//Software distributed under the License is distributed on an "AS IS" basis,
//WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//for the specific language governing rights and
//limitations under the License.
//
//The Original Code is "The Columba Project"
//
//The Initial Developers of the Original Code are Frederik Dietz and Timo
// Stich.
//Portions created by Frederik Dietz and Timo Stich are Copyright (C) 2003.
//
//All Rights Reserved.
package org.columba.calendar.model;

import org.columba.calendar.model.api.IComponent;
import org.columba.calendar.model.api.IComponentInfo;
import org.columba.calendar.model.api.IComponent.TYPE;

public class ComponentInfo implements IComponentInfo {

	private String id;

	private TYPE type;
	
	private String calendarId;
	
	private IComponent component;

	public ComponentInfo(String id, TYPE type, String calendarId, IComponent component) {
		if (id == null)
			throw new IllegalArgumentException("id == null");
		if (type == null)
			throw new IllegalArgumentException("type == null");
		if (calendarId == null)
			throw new IllegalArgumentException("calendarId == null");
		if (component == null)
			throw new IllegalArgumentException("component == null");
		
		this.id = id;
		this.type = type;
		this.calendarId = calendarId;
	}

	public TYPE getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public String getCalendar() {
		return calendarId;
	}

	public IComponent getComponent() {
		return component;
	}

	public void setCalendar(String calendar) {
		this.calendarId = calendar;	
	}

}
