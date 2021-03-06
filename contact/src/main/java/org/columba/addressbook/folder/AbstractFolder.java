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
package org.columba.addressbook.folder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;

import org.columba.addressbook.config.FolderItem;
import org.columba.addressbook.model.ContactModelFactory;
import org.columba.addressbook.model.IContactModel;
import org.columba.addressbook.model.IContactModelPartial;
import org.columba.api.command.IWorkerStatusController;
import org.columba.api.exception.StoreException;

/**
 * Abstract base class for every contact folder.
 *
 * @author fdietz
 *
 */
public abstract class AbstractFolder extends AddressbookTreeNode implements
		IContactStorage, IContactFolder {

	protected EventListenerList listenerList = new EventListenerList();

	protected int nextContactUid = 0;

	/**
	 * FolderItem keeps information about the folder for example: name,
	 * accessrights, type
	 */

	protected ContactItemCacheStorage cacheStorage = null;

	public AbstractFolder(String name, String dir) {
		super(name);
	}

	public AbstractFolder(FolderItem item) {
		super(item);
	}

	/**
	 * Adds a listener.
	 */
	public void addFolderListener(FolderListener l) {
		listenerList.add(FolderListener.class, l);
	}

	/**
	 * Removes a previously registered listener.
	 */
	public void removeFolderListener(FolderListener l) {
		listenerList.remove(FolderListener.class, l);
	}

	/**
	 * Propagates an event to all registered listeners notifying them of a item
	 * addition.
	 */
	protected void fireItemAdded(String uid) {

		IFolderEvent e = new FolderEvent(this, uid);
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == FolderListener.class) {
				((FolderListener) listeners[i + 1]).itemAdded(e);
			}
		}
	}

	/**
	 * Propagates an event to all registered listeners notifying them of a item
	 * removal.
	 */
	protected void fireItemRemoved(String uid) {

		IFolderEvent e = new FolderEvent(this, uid);
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == FolderListener.class) {
				((FolderListener) listeners[i + 1]).itemRemoved(e);
			}
		}
	}

	/**
	 * Propagates an event to all registered listeners notifying them of a item
	 * change.
	 */
	protected void fireItemChanged(String uid) {

		IFolderEvent e = new FolderEvent(this, uid);
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == FolderListener.class) {
				((FolderListener) listeners[i + 1]).itemChanged(e);
			}
		}
	}

	public void createChildren(IWorkerStatusController worker) {
	}

	public ContactItemCacheStorage getCacheStorage() {
		return cacheStorage;
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#getHeaderItemList()
	 */
	public Map<String, IContactModelPartial> getContactItemMap()
			throws StoreException {
		return getCacheStorage().getContactItemMap();
	}

	/**
	 * @see org.columba.addressbook.folder.IContactFolder#getContactItemMap(java.lang.String[])
	 */
	public Map<String, IContactModelPartial> getContactItemMap(String[] ids)
			throws StoreException {
		return getCacheStorage().getContactItemMap(ids);
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#findByEmailAddress(java.lang.String)
	 */
	public String findByEmailAddress(String emailAddress) throws StoreException {
		Iterator<IContactModelPartial> it = getContactItemMap().values().iterator();
		while (it.hasNext()) {
			IContactModelPartial item = (IContactModelPartial) it.next();
			String address = item.getAddress();

			if (address.equalsIgnoreCase(emailAddress)) {
				String id = item.getId();
				return id;
			}
		}
		return null;
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#findByName(java.lang.String)
	 */
	public String findByName(String name) throws StoreException {
		Iterator<IContactModelPartial> it = getContactItemMap().values().iterator();
		while (it.hasNext()) {
			IContactModelPartial item = (IContactModelPartial) it.next();
			String sortas = item.getName();
			String lastname = item.getLastname();
			String firstname = item.getFirstname();

			if (name.equalsIgnoreCase(sortas)) {
				String id = item.getId();
				return id;
			} else if (name.equalsIgnoreCase(lastname)) {
				String id = item.getId();
				return id;
			} else if (name.equalsIgnoreCase(firstname)) {
				String id = item.getId();
				return id;
			}
		}
		return null;
	}

	/**
	 * save header-cache (HeaderItemList)
	 */
	public void save() throws StoreException {

	}

	/**
	 * load header-cache (HeaderItemList)
	 */
	public void load() throws StoreException {

	}

	/**
	 * @see javax.swing.tree.DefaultMutableTreeNode#getPathToRoot(TreeNode, int)
	 */
	/*
	 * protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
	 * TreeNode[] retNodes;
	 *
	 * if (aNode == null) { if (depth == 0) { return null; } else { retNodes =
	 * new TreeNode[depth]; } } else { depth++; retNodes =
	 * getPathToRoot(aNode.getParent(), depth); retNodes[retNodes.length -
	 * depth] = aNode; }
	 *
	 * return retNodes; }
	 */

	/**
	 * Method getTreePath.
	 *
	 * @return String
	 */
	/*
	 * public String getTreePath() { TreeNode[] treeNode = getPathToRoot(this,
	 * 0);
	 *
	 * StringBuffer path = new StringBuffer();
	 *
	 * for (int i = 1; i < treeNode.length; i++) { AddressbookTreeNode folder =
	 * (AddressbookTreeNode) treeNode[i]; path.append("/" + folder.getName()); }
	 *
	 * return path.toString(); }
	 */

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#add(IContactModel)
	 */
	public String add(IContactModel contact) throws StoreException {
		String uid = generateNextMessageUid();

		IContactModelPartial item = ContactModelFactory
				.createContactModelPartial(contact, uid);

		getCacheStorage().add(uid, item);

		return uid;
	}

	/**
	 *
	 * @see org.columba.addressbook.folder.IContactStorage#modify(java.lang.Object,
	 *      IContactModel)
	 */
	public void modify(String uid, IContactModel contact) throws StoreException {

		IContactModelPartial item = ContactModelFactory
				.createContactModelPartial(contact, uid);

		getCacheStorage().modify(uid, item);
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#remove(java.lang.Object)
	 */
	public void remove(String uid) throws StoreException {
		getCacheStorage().remove(uid);
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#get(java.lang.Object)
	 */
	public abstract IContactModel get(String uid) throws StoreException;

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#count()
	 */
	public int count() throws StoreException {
		return getCacheStorage().count();
	}

	/**
	 * @see org.columba.addressbook.folder.IContactStorage#exists(java.lang.Object)
	 */
	public boolean exists(String uid) throws StoreException {
		return getCacheStorage().exists(uid);
	}

	/**
	 * @see org.columba.addressbook.folder.IContactFolder#getHeaderItemList()
	 */
	public List<IContactModelPartial> getHeaderItemList() throws StoreException {
		// create list containing all contact item of this folder
		List<IContactModelPartial> list = new ArrayList<IContactModelPartial>(
				getContactItemMap().values());

		return list;
	}

	/**
	 * @return Returns the messageUid.
	 */
	public int getNextContactUid() {
		while(exists(new Integer(nextContactUid).toString())) {
			nextContactUid++;
		}
		return nextContactUid;
	}

	private String generateNextMessageUid() {
		getNextContactUid();
		return new Integer(nextContactUid++).toString();
	}
}