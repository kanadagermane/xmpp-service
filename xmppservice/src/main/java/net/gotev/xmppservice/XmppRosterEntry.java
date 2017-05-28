package net.gotev.xmppservice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import org.jxmpp.jid.Jid;

/**
 * Represents an entry in the roster.
 * @author gotev (Aleksandar Gotev)
 */
public class XmppRosterEntry implements Comparable<XmppRosterEntry> {

    private Jid xmppJID;
    private byte[] avatar;
    private String alias;
    private boolean available;
    private int presenceMode;
    private String personalMessage;
    private long unreadMessages;

    public Jid getXmppJID() {
        return xmppJID;
    }

    public XmppRosterEntry setXmppJID(Jid xmppJID) {
        this.xmppJID = xmppJID;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public XmppRosterEntry setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public XmppRosterEntry setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public int getPresenceMode() {
        return presenceMode;
    }

    public XmppRosterEntry setPresenceMode(int presenceMode) {
        this.presenceMode = presenceMode;
        return this;
    }

    public String getPersonalMessage() {
        return personalMessage;
    }

    public XmppRosterEntry setPersonalMessage(String personalMessage) {
        this.personalMessage = personalMessage;
        return this;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public Drawable getAvatarDrawable(Context context) {
        if (avatar == null || avatar.length < 3) return null;
        return new BitmapDrawable(context.getResources(),
                                  BitmapFactory.decodeByteArray(avatar, 0, avatar.length));
    }

    public XmppRosterEntry setAvatar(byte[] avatar) {
        this.avatar = avatar;
        return this;
    }

    public long getUnreadMessages() {
        return unreadMessages;
    }

    public XmppRosterEntry setUnreadMessages(long unreadMessages) {
        this.unreadMessages = unreadMessages;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XmppRosterEntry that = (XmppRosterEntry) o;

        return xmppJID.equals(that.xmppJID);

    }

    @Override
    public int hashCode() {
        return xmppJID.hashCode();
    }

    @Override
    public int compareTo(XmppRosterEntry another) {
        if (unreadMessages > another.unreadMessages)
            return -1;

        if (unreadMessages < another.unreadMessages)
            return 1;

        int cmp = comparePresence(another);

        if (cmp == 0) {
            String nameA = alias;
            if (nameA == null || nameA.isEmpty()) {
                nameA = xmppJID.toString();
            }

            String nameB = another.alias;
            if (nameB == null || nameB.isEmpty()) {
                nameB = another.xmppJID.toString();
            }

            cmp = nameA.compareTo(nameB);
        }

        return cmp;
    }

    private int comparePresence(XmppRosterEntry other) {
        if (available && !other.available) {
            return -1;
        }

        if (!available && other.available) {
            return 1;
        }

        if (presenceMode < other.presenceMode) {
            return -1;
        }

        if (presenceMode > other.presenceMode) {
            return 1;
        }

        return 0;
    }
}
