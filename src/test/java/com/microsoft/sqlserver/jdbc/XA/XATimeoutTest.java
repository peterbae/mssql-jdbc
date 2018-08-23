package com.microsoft.sqlserver.jdbc.XA;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import com.microsoft.sqlserver.testframework.AbstractTest;


@RunWith(JUnitPlatform.class)
public class XATimeoutTest extends AbstractTest {

    @Test
    public void testXATimeoutPersist() throws Exception {
        // Create variables for the connection string.
        String prefix = "jdbc:sqlserver://";
        int portNumber = 1433;
        int xaSleep;
        int xaTimeout;
        String serverName = "localhost";
        String databaseName = "test";
        String user = "sa";
        String password = "password";
        String tableName = "XAMin" + System.currentTimeMillis();

        // Set xaTimeout to 5, then xaSleep to 0. then run this java file. it should succeed.
        // Afterwards, set xaTimeout to 0, then xaSleep to 8000. Then run this java file.
        // Now, this case should succeed since we set xaTimeout to 0 (infinite), but it fails
        // because the timeout value of 5 seconds is still persisting, and we're sleeping on this
        // transaction for more than 5 seconds (in this case, 8 seconds).
        // If you set the xaTimeout to something larger than 8 seconds in the first run, it doesn't fail.

         xaTimeout = 5;
         xaSleep = 0;

        String connectionUrl = prefix + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";user="
                + user + ";password=" + password;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(connectionUrl);

            // Create a test table.
            Statement stmt = con.createStatement();
            try {
                stmt.executeUpdate("DROP TABLE " + tableName);
            } catch (Exception e) {}
            stmt.executeUpdate("CREATE TABLE " + tableName + " (f1 int, f2 varchar(max))");
            stmt.close();
            con.close();

            // Create the XA data source and XA ready connection.
            SQLServerXADataSource ds = new SQLServerXADataSource();
            ds.setUser(user);
            ds.setPassword(password);
            ds.setServerName(serverName);
            ds.setPortNumber(portNumber);
            ds.setDatabaseName(databaseName);
            XAConnection xaCon = ds.getXAConnection();
            con = xaCon.getConnection();

            // Get a unique Xid object for testing.
            XAResource xaRes = null;
            Xid xid = null;
            xid = XidImpl.getUniqueXid(1);

            // Get the XAResource object and set the timeout value.
            xaRes = xaCon.getXAResource();
            xaRes.setTransactionTimeout(xaTimeout);

            // Perform the XA transaction.
            xaRes.start(xid, XAResource.TMNOFLAGS);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (f1,f2) VALUES (?, ?)");
            pstmt.setInt(1, 1);
            pstmt.setString(2, xid.toString());
            pstmt.executeUpdate();

            Thread.sleep(xaSleep);

            // Commit the transaction.
            xaRes.end(xid, XAResource.TMSUCCESS);
            xaRes.commit(xid, true);

            // Cleanup.
            con.close();
            xaCon.close();

            // Open a new connection and read back the record to verify that it worked.
            con = DriverManager.getConnection(connectionUrl);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + tableName);
            rs.next();
            rs.close();
            con.close();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        
        xaTimeout = 0;
        xaSleep = 8000;
        
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(connectionUrl);

            // Create a test table.
            Statement stmt = con.createStatement();
            try {
                stmt.executeUpdate("DROP TABLE " + tableName);
            } catch (Exception e) {}
            stmt.executeUpdate("CREATE TABLE " + tableName + " (f1 int, f2 varchar(max))");
            stmt.close();
            con.close();

            // Create the XA data source and XA ready connection.
            SQLServerXADataSource ds = new SQLServerXADataSource();
            ds.setUser(user);
            ds.setPassword(password);
            ds.setServerName(serverName);
            ds.setPortNumber(portNumber);
            ds.setDatabaseName(databaseName);
            XAConnection xaCon = ds.getXAConnection();
            con = xaCon.getConnection();

            // Get a unique Xid object for testing.
            XAResource xaRes = null;
            Xid xid = null;
            xid = XidImpl.getUniqueXid(1);

            // Get the XAResource object and set the timeout value.
            xaRes = xaCon.getXAResource();

            // Perform the XA transaction.
            xaRes.start(xid, XAResource.TMNOFLAGS);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (f1,f2) VALUES (?, ?)");
            pstmt.setInt(1, 1);
            pstmt.setString(2, xid.toString());
            pstmt.executeUpdate();

            Thread.sleep(xaSleep);

            // Commit the transaction.
            xaRes.end(xid, XAResource.TMSUCCESS);
            xaRes.commit(xid, true);

            // Cleanup.
            con.close();
            xaCon.close();

            // Open a new connection and read back the record to verify that it worked.
            con = DriverManager.getConnection(connectionUrl);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + tableName);
            rs.next();
            rs.close();
            con.close();
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class XidImpl implements Xid {

    public int formatId;

    public byte[] gtrid;

    public byte[] bqual;

    public byte[] getGlobalTransactionId() {
            return gtrid;
    }

    public byte[] getBranchQualifier() {
            return bqual;
    }

    public int getFormatId() {
            return formatId;
    }

    XidImpl(int formatId, byte[] gtrid, byte[] bqual) {

            this.formatId = formatId;

            this.gtrid = gtrid;

            this.bqual = bqual;

    }

    public String toString() {

            int hexVal;

            StringBuffer sb = new StringBuffer(512);

            sb.append("formatId=" + formatId);

            sb.append(" gtrid(" + gtrid.length + ")={0x");

            for (int i = 0; i < gtrid.length; i++) {

                    hexVal = gtrid[i] & 0xFF;

                    if (hexVal < 0x10)

                            sb.append("0" + Integer.toHexString(gtrid[i] & 0xFF));

                    else

                            sb.append(Integer.toHexString(gtrid[i] & 0xFF));

            }

            sb.append("} bqual(" + bqual.length + ")={0x");

            for (int i = 0; i < bqual.length; i++) {

                    hexVal = bqual[i] & 0xFF;

                    if (hexVal < 0x10)

                            sb.append("0" + Integer.toHexString(bqual[i] & 0xFF));

                    else

                            sb.append(Integer.toHexString(bqual[i] & 0xFF));

            }

            sb.append("}");

            return sb.toString();

    }

    // Returns a globally unique transaction id.

    static byte[] localIP = null;

    static int txnUniqueID = 0;

    static Xid getUniqueXid(int tid) {

            Random rnd = new Random(System.currentTimeMillis());

            txnUniqueID++;

            int txnUID = txnUniqueID;

            int tidID = tid;

            int randID = rnd.nextInt();

            byte[] gtrid = new byte[64];

            byte[] bqual = new byte[64];

            if (null == localIP) {

                    try {

                            localIP = Inet4Address.getLocalHost().getAddress();

                    }

                    catch (Exception ex) {

                            localIP = new byte[] { 0x01, 0x02, 0x03, 0x04 };

                    }

            }

            System.arraycopy(localIP, 0, gtrid, 0, 4);

            System.arraycopy(localIP, 0, bqual, 0, 4);

            // Bytes 4 -> 7 - unique transaction id.

            // Bytes 8 ->11 - thread id.

            // Bytes 12->15 - random number generated by using seed from current
            // time in milliseconds.

            for (int i = 0; i <= 3; i++) {

                    gtrid[i + 4] = (byte) (txnUID % 0x100);

                    bqual[i + 4] = (byte) (txnUID % 0x100);

                    txnUID >>= 8;

                    gtrid[i + 8] = (byte) (tidID % 0x100);

                    bqual[i + 8] = (byte) (tidID % 0x100);

                    tidID >>= 8;

                    gtrid[i + 12] = (byte) (randID % 0x100);

                    bqual[i + 12] = (byte) (randID % 0x100);

                    randID >>= 8;

            }

            return new XidImpl(0x1234, gtrid, bqual);
    }
}

