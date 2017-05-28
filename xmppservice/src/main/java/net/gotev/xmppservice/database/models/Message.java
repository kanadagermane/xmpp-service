package net.gotev.xmppservice.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import net.gotev.xmppservice.database.DatabaseModel;
import net.gotev.xmppservice.database.tables.MessagesTable;

import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

import java.util.Date;

import static net.gotev.xmppservice.database.SqLiteDatabase.getIntFromCursor;
import static net.gotev.xmppservice.database.SqLiteDatabase.getLongFromCursor;
import static net.gotev.xmppservice.database.SqLiteDatabase.getStringFromCursor;

/**
 * @author Aleksandar Gotev
 */
public class Message implements DatabaseModel {

    private long id = -1;
    private long creationTimestamp;
    private Jid account;
    private Jid remoteAccount;
    private int direction;
    private String message;
    private int status;
    private int sentTimestamp;
    private int deliveredTimestamp;
    private int readTimestamp;

    public Message() {
        creationTimestamp = new Date().getTime();
    }

    @Override
    public void setValuesFromCursor(Cursor cursor) {
        id = getLongFromCursor(cursor, MessagesTable.COL_ID);
        creationTimestamp = getLongFromCursor(cursor, MessagesTable.COL_CREATION_TIMESTAMP);
        try {
            account = JidCreate.bareFrom(getStringFromCursor(cursor, MessagesTable.COL_ACCOUNT));
        } catch (Exception ex) {}

        try {
            remoteAccount =  JidCreate.bareFrom(getStringFromCursor(cursor, MessagesTable.COL_REMOTE_ACCOUNT));
        } catch (Exception ex) {}


        direction = getIntFromCursor(cursor, MessagesTable.COL_DIRECTION);
        message = getStringFromCursor(cursor, MessagesTable.COL_MESSAGE);
        status =  getIntFromCursor(cursor, MessagesTable.COL_STATUS);
        sentTimestamp = getIntFromCursor(cursor, MessagesTable.COL_SENT_TIMESTAMP);
        deliveredTimestamp = getIntFromCursor(cursor, MessagesTable.COL_DELIVERED_TIMESTAMP);
        readTimestamp = getIntFromCursor(cursor, MessagesTable.COL_READ_TIMESTAMP);
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues out = new ContentValues();

        if (id > 0) {
            out.put(MessagesTable.COL_ID, id);
        }

        out.put(MessagesTable.COL_CREATION_TIMESTAMP, creationTimestamp);
        out.put(MessagesTable.COL_ACCOUNT, account.toString());
        out.put(MessagesTable.COL_REMOTE_ACCOUNT, remoteAccount.toString());
        out.put(MessagesTable.COL_DIRECTION, direction);
        out.put(MessagesTable.COL_MESSAGE, message);
        out.put(MessagesTable.COL_STATUS, status);
        out.put(MessagesTable.COL_SENT_TIMESTAMP, sentTimestamp);
        out.put(MessagesTable.COL_DELIVERED_TIMESTAMP, deliveredTimestamp);
        out.put(MessagesTable.COL_READ_TIMESTAMP, readTimestamp);

        return out;
    }

    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public Jid getAccount() {
        return account;
    }

    public void setAccount(Jid account) {
        this.account = account;
    }

    public Jid getRemoteAccount() {
        return remoteAccount;
    }

    public void setRemoteAccount(Jid remoteAccount) {
        this.remoteAccount = remoteAccount;
    }

    public int getDirection() {
        return direction;
    }

    public MessagesTable.Direction getDirectionAsEnum() {
        return MessagesTable.Direction.values()[direction];
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setDirection(MessagesTable.Direction direction) {
        this.direction = direction.ordinal();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public MessagesTable.Status getStatusAsEnum() {
        return MessagesTable.Status.values()[status];
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(MessagesTable.Status status) {
        this.status = status.ordinal();
    }

    public int getSentTimestamp() {
        return sentTimestamp;
    }

    public void setSentTimestamp(int sentTimestamp) {
        this.sentTimestamp = sentTimestamp;
    }

    public int getDeliveredTimestamp() {
        return deliveredTimestamp;
    }

    public void setDeliveredTimestamp(int deliveredTimestamp) {
        this.deliveredTimestamp = deliveredTimestamp;
    }

    public int getReadTimestamp() {
        return readTimestamp;
    }

    public void setReadTimestamp(int readTimestamp) {
        this.readTimestamp = readTimestamp;
    }
}
