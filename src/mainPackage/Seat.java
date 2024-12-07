package mainPackage;

public class Seat {
    private String SeatNumber;
    private boolean IsAvailable;
    private String SeatClass;
    public Seat(String seatNumber) {
        this.SeatNumber = seatNumber;


    }
    //constructor
    public Seat(String seatNumber, String seatClass) {
        this.SeatNumber = seatNumber;
        this.IsAvailable = true;
        this.SeatClass = seatClass;
    }
   //getters and setters
    public String getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.SeatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        this.IsAvailable = available;
    }

    public String getSeatClass() {
        return SeatClass;
    }

    public void setSeatClass(String seatClass) {
        this.SeatClass = seatClass;
    }

    @Override
    public String toString() {
        return "SeatNumber:" + SeatNumber+ ","
                 + (IsAvailable? "Available" : "Booked") + ", SeatClass:" + SeatClass + '.';

    }

    public void cancelBooking()
   {
        if (!IsAvailable) {
            IsAvailable = true;
            System.out.println("Booking for seat " + SeatNumber + " has been canceled.");
        } else {
            System.out.println("Seat " + SeatNumber + " is already available.");
        }
    }
    }


