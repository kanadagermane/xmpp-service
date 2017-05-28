package net.gotev.xmppservice;

import com.google.gson.Gson;

import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Xmpp Account configuration.
 * @author gotev (Aleksandar Gotev)
 */
public class XmppAccount {

    // presence mode constants
    public static final int PRESENCE_MODE_CHAT = 0;
    public static final int PRESENCE_MODE_AVAILABLE = 1;
    public static final int PRESENCE_MODE_AWAY = 2;
    public static final int PRESENCE_MODE_XA = 3;
    public static final int PRESENCE_MODE_DND = 4;

    private String xmppJid;
    private String serviceName;
    private String password;
    private String host;

    public InetAddress getHostAdress() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(hostAdress);
        } catch (UnknownHostException ex) {

        }

        return inetAddress;
    }

    public void setHostAdress(String hostAdress) {
        this.hostAdress = hostAdress;
    }

    private String hostAdress;
    private int port;
    private int priority;
    private String resourceName;
    private String personalMessage;
    private int presenceMode;

    public Jid getXmppJid() {
        try {
            return JidCreate.bareFrom(xmppJid);
        } catch (Exception ex) {}
        return null;
    }

    public void setXmppJid(Jid xmppJid) {
        this.xmppJid = xmppJid.toString();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getPersonalMessage() {
        return personalMessage == null ? "" : personalMessage;
    }

    public void setPersonalMessage(String personalMessage) {
        this.personalMessage = personalMessage;
    }

    public int getPresenceMode() {
        return presenceMode;
    }

    /**
     * Sets the presence mode for this account.
     * @param presenceMode integer value indicating the presence mode. Use constants defined in this
     *                     class, for example {@link XmppAccount#PRESENCE_MODE_AVAILABLE}.
     */
    public void setPresenceMode(int presenceMode) {
        this.presenceMode = presenceMode;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static XmppAccount fromJson(String json) {
        return new Gson().fromJson(json, XmppAccount.class);
    }
}
