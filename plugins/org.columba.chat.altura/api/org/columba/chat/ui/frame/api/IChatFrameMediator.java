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
package org.columba.chat.ui.frame.api;

import org.columba.api.gui.frame.IFrameMediator;
import org.columba.chat.ui.conversation.api.IConversationController;
import org.columba.chat.ui.presence.api.IPresenceController;
import org.columba.chat.ui.roaster.api.IRoasterController;

/**
 * @author fdietz
 *  
 */
public interface IChatFrameMediator extends IFrameMediator {

	IRoasterController getRoasterTree();
	
	IPresenceController getPresenceController();
	
	IConversationController getConversationController();
}