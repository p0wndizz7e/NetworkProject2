
/**
 * A class which represents the receiver transport layer
 */
public class ReceiverTransport
{
    private ReceiverApplication ra;
    private NetworkLayer nl;
    private boolean bufferingPackets;
    
    private int seqNum;
    private int ackNum;

    public ReceiverTransport(NetworkLayer nl){
        ra = new ReceiverApplication();
        this.nl=nl;
        initialize();
    }

    public void initialize()
    {
    }

    public void receiveMessage(Packet pkt)
    {
        int pktAck = pkt.getAcknum();
        int pktSeq = pkt.getSeqnum();
        if(pkt.isCorrupt())
        {
            System.out.println("Corrupt");
        }else{
            System.out.println("Not Corropt");
            //Ack it i guess
            ra.receiveMessage(pkt.getMessage());
        }
    }

    public void setProtocol(int n)
    {
        if(n>0)
            bufferingPackets=true;
        else
            bufferingPackets=false;
    }

}
