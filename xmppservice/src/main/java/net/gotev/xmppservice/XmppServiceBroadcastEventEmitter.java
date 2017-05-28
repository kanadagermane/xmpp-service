package net.gotev.xmppservice;

import android.content.Context;
import android.content.Intent;

import org.jxmpp.jid.Jid;

/**
 * Emits the xmpp service broadcast intents.
 * @author gotev (Aleksandar Gotev)
 */
public class XmppServiceBroadcastEventEmitter {

    public static final String BROADCAST_CONNECTED = ".xmpp.connected";
    public static final String BROADCAST_DISCONNECTED = ".xmpp.disconnected";
    public static final String BROADCAST_ROSTER_CHANGED = ".xmpp.rosterchanged";
    public static final String BROADCAST_MESSAGE_ADDED = ".xmpp.messageadded";
    public static final String BROADCAST_MESSAGE_DELETED = ".xmpp.messagedeleted";
    public static final String BROADCAST_CONTACT_ADDED = ".xmpp.contactadded";
    public static final String BROADCAST_CONTACT_ADD_ERROR = ".xmpp.contactadderror";
    public static final String BROADCAST_CONTACT_REMOVED = ".xmpp.contactremoved";
    public static final String BROADCAST_CONVERSATIONS_CLEARED = ".xmpp.conversationscleared";
    public static final String BROADCAST_CONVERSATIONS_CLEAR_ERROR = ".xmpp.conversationsclearerror";
    public static final String BROADCAST_CONTACT_RENAMED = ".xmpp.contactrenamed";
    public static final String BROADCAST_MESSAGE_SENT = ".xmpp.messagesent";

    public static final String PARAM_REMOTE_ACCOUNT = "remoteAccount";
    public static final String PARAM_ALIAS = "alias";
    public static final String PARAM_MESSAGE_ID = "messageId";
    public static final String PARAM_INCOMING = "incoming";

    private static Context mContext;
    private static String mNamespace;

    /**
     * Private constructor to avoid instantiation.
     */
    private XmppServiceBroadcastEventEmitter() { }

    public static synchronized void initialize(Context context, String namespace) {
        mContext = context;
        mNamespace = namespace;
    }

    public static String getNamespace() {
        return mNamespace;
    }

    public static synchronized void broadcastRosterChanged() {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_ROSTER_CHANGED);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastXmppConnected() {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONNECTED);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastXmppDisconnected() {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_DISCONNECTED);
        //mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastMessageAdded(Jid remoteAccount, boolean incoming) {
        Intent broadcast = new Intent();

        broadcast.setAction(mNamespace + BROADCAST_MESSAGE_ADDED);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount.toString());
        broadcast.putExtra(PARAM_INCOMING, incoming);

        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastMessageDeleted(long messageId) {
        Intent broadcast = new Intent();

        broadcast.setAction(mNamespace + BROADCAST_MESSAGE_DELETED);
        broadcast.putExtra(PARAM_MESSAGE_ID, messageId);

        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastContactAdded(String remoteAccount) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONTACT_ADDED);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastContactAddError(String remoteAccount) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONTACT_ADD_ERROR);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastContactRemoved(String remoteAccount) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONTACT_REMOVED);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastConversationsCleared(String remoteAccount) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONVERSATIONS_CLEARED);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastConversationsClearError(String remoteAccount) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONVERSATIONS_CLEAR_ERROR);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastContactRenamed(String remoteAccount, String newAlias) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_CONTACT_RENAMED);
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount);
        broadcast.putExtra(PARAM_ALIAS, newAlias);
        mContext.sendBroadcast(broadcast);
    }

    public static synchronized void broadcastMessageSent(long messageId) {
        Intent broadcast = new Intent();
        broadcast.setAction(mNamespace + BROADCAST_MESSAGE_SENT);
        broadcast.putExtra(PARAM_MESSAGE_ID, messageId);
        mContext.sendBroadcast(broadcast);
    }
}
