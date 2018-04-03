import java.util.*;

/**
 * A class which represents a packet
 */
public class Packet
{
    
    private Message msg; //the enclosed message
    private int seqnum; //packets seq. number
    private int acknum; //packet ack. number
    private int checksum; //packet checksum

    Random ran; //random number generator

    public Packet(Message msg, int seqnum, int acknum, int checksum)
    {
        this.msg=msg;
        this.seqnum=seqnum;
        this.acknum=acknum;
        this.checksum=checksum;
        this.ran=new Random();
    }
    
    public Packet(Packet other)
    {
        this.msg=new Message(new String(other.msg.getMessage()));
        this.seqnum=other.seqnum;
        this.acknum=other.acknum;
        this.checksum=other.checksum;
        this.ran=other.ran;
        
    }

    public int getAcknum()
    {
        return acknum;
    }
    
    public int getSeqnum()
    {
        return seqnum;
    }

    public Message getMessage()
    {
        return msg;
    }
    
    public void setChecksum() //make sure checksum includes teh ack and seq numbers
    {
        int tempSum = 0;
        String tempMsg = msg.getMessage();
        int msgLength = tempMsg.length();
        for(int x = 0; x < msgLength; x++)
        {
            tempSum += (int)tempMsg.charAt(x);
        }
        tempSum += seqnum + acknum;
        int ones = (Integer.highestOneBit(tempSum) << 1) - 1;
        int compliment = tempSum ^ ones;
        checksum = compliment;
        System.out.println("tempsum " + tempSum + " compliment " + compliment + " addition " + (tempSum + compliment));
    }
    
    public boolean isCorrupt()
    {
        int tempSum = 0;
        String tempMsg = msg.getMessage();
        int msgLength = tempMsg.length();
        for(int x = 0; x < msgLength; x++)
        {
            tempSum += (int)tempMsg.charAt(x);
        }
        tempSum += seqnum + acknum;
        int result = tempSum + checksum;
        String binary = Integer.toBinaryString(result);
        System.out.println(binary);
        for(int i = 0; i < binary.length(); i ++)
        {
            if(binary.charAt(i) == '0')
            {
                System.out.println("Yall done goofed");
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method curropts the packet the follwing way:
     * curropt the message with a 75% chance
     * curropt the seqnum with 12.5% chance
     * curropt the ackum with 12.5% chance
     */
    public void corrupt()
    {
        double num =ran.nextDouble();
        
        if(num<0.75)
        {this.msg.corruptMessage();}
        else if(num<0.875)
        {this.seqnum=this.seqnum+1;}
        else
        {this.acknum=this.acknum+1;}

    }
    

}
