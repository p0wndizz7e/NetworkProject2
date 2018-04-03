
import java.util.ArrayList;
/**
 * A class which represents the receiver transport layer
 */
public class SenderTransport
{
    private NetworkLayer nl;
    private Timeline tl;
    private int n; //windows size
    private int mss; //max message size
    private boolean bufferingPackets;
    
    private int seqNum;
    private int ackNum;

    public SenderTransport(NetworkLayer nl){
        this.nl=nl;
        initialize();

    }

    public void initialize()
    {
        seqNum = 0;
        ackNum = 0;
    }

    public void sendMessage(Message msg)
    {
        //MOdify the ack and sequence numbers
        Packet pkt = new Packet(msg, seqNum, ackNum, 0);
        pkt.setChecksum();
        
    }

    public void receiveMessage(Packet pkt)//This is all about the acks my guy
    {
        //check the ack and seq numbers
    }

    public void timerExpired()
    { 
        //rest windows/ resend message
    }

    public void setTimeLine(Timeline tl)
    {
        this.tl=tl;
    }

    public void setWindowSize(int n)
    {
        this.n=n;
    }
    
    public void setMSS(int n)
    {
        this.n=n;
    }
    

    public void setProtocol(int n)
    {
        if(n>0)
            bufferingPackets=true;
        else
            bufferingPackets=false;
    }

}
