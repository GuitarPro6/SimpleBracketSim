package assignment12;

import java.util.TreeSet;
import assignment13.FlightCriteria;

/**
 * This class creates and maintains a flight object that contains all the information about the flights origin, destination, carriers, delay, time, cost, and cancellation status. 
 * In the graph this class builds the edge between two vertices. When the data is aggregated, it is all added and averaged to the first flight that created the link between the origin
 * and destination airports. 
 * @author Jonathan Bown U0696785 Samuel Lyons U0861439
 */
public class Flight2
{
    /** the flight's origin airport */
    private String origin;

    /** the flight's destination airport */
    private String destination;

    /** The set of carriers who have this flight */
    private TreeSet<String> carriers;

    /** How long the flight is going to be delayed */
    private double delay, totalDelay;

    /**
     * A 0 or a 1 representing whether or not the flight's been canceled.
     * 
     * 0 if not, 1 if so
     */
    private double canceled, totalCanceled;

    /** When the flight leaves */
    private double time, totalTime;

    /** The distance between the origin and destination airports */
    private double distance, totalDistance;

    /** How much the flight costs */
    private double cost, totalCost;

    /** The number of duplicate flights */
    private int count, delayCount, canceledCount, timeCount, distanceCount, costCount;

    /**
     * Constructs a flight given a string with all the flight info
     * @param flightInfo - a string containing all necessary fields to parse into the flight
     */
    public Flight2 (String flightInfo)
    {
        String[] parts = flightInfo.split(",");

        origin = parts[0];
        destination = parts[1];
        carriers = new TreeSet<>();
        carriers.add(parts[2]);
        delay = Double.parseDouble(parts[3]);
        totalDelay = 0;
        canceled = Double.parseDouble(parts[4]);
        totalCanceled = 0;
        time = Double.parseDouble(parts[5]);
        totalTime = 0;
        distance = Double.parseDouble(parts[6]);
        totalDistance = 0;
        cost = Double.parseDouble(parts[7]);
        totalCost = 0;
        
        if (delay >= 0)
        {
            totalDelay = delay;
            delayCount = 1;
        }
        if (canceled >= 0)
        {
            totalCanceled = canceled;
            canceledCount = 1;
        }
        if (time >= 0)
        {
            totalTime = time;
            timeCount = 1;
        }
        if (distance >= 0)
        {
            totalDistance = distance;
            distanceCount = 1;
        }
        if (cost >= 0)
        {
            totalCost = cost;
            costCount = 1;
        }

        setCount(1);
    }

    /**
     * @return the count
     */
    public int getCount ()
    {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount (int count)
    {
        this.count = count;
    }

    /**
     * @return the origin
     */
    public String getOrigin ()
    {
        return origin;
    }

    /**
     * @return the destination
     */
    public String getDestination ()
    {
        return destination;
    }

    /**
     * @return the carrier
     */
    public TreeSet<String> getCarriers ()
    {
        return carriers;
    }

    /**
     * @return the delay
     */
    public double getDelay ()
    {
        return delay;
    }

    /**
     * @return the canceled
     */
    public double getCanceled ()
    {
        return canceled;
    }

    /**
     * @return the time
     */
    public double getTime ()
    {
        return time;
    }

    /**
     * @return the distance
     */
    public double getDistance ()
    {
        return distance;
    }

    /**
     * @return the cost
     */
    public double getCost ()
    {
        return cost;
    }

    /**
     * Adds the various criteria weights together for later averaging
     * 
     * @param carriers -TreeSet of carriers
     * @param delay - double representing delay time
     * @param canceled - double 
     * @param time - double representing time of flight
     * @param distance - double representing distance traveled
     * @param cost - double representing this flight's cost. 
     */
    public void aggregateCriteria (TreeSet<String> carriers, double delay, double canceled, double time,
            double distance, double cost)
    {
        setCount(getCount() + 1);
        this.carriers.addAll(carriers);
        if (!(delay < 0))
        {
            delayCount += 1;
            this.totalDelay += (delay);
        }

        if (!(canceled < 0))
        {
            this.totalCanceled += canceled;
            canceledCount += 1;
        }
        if (!(time < 0))
        {
            this.totalTime += time;
            timeCount += 1;
        }
        if (!(distance < 0))
        {
            this.totalDistance += distance;
            distanceCount += 1;
        }
        if (!(cost < 0))
        {
            this.totalCost += cost;
            costCount += 1;
        }
    }
    
    /**
     * Averages the cost fields for this flight
     */

    public void averageWeights ()
    {
        delay = totalDelay / delayCount;
        canceled = totalCanceled / canceledCount;
        time = totalTime / timeCount;
        distance = totalDistance / distanceCount;
        cost = totalCost / costCount;
    }

    /**
     * Gets the weight associated with the given FlightCriteria. e.g., if the user wants the cheapest path based on
     * FlightCriteria.COST, we would simply return the cost for that flight
     * 
     * @param criteria
     *            <li>COST
     *            <li>DELAY
     *            <li>DISTANCE
     *            <li>CANCELED
     *            <li>TIME
     * @return the weight associated with the specified criteria
     */
    public double getCriteriaCost (FlightCriteria criteria)
    {
        switch (criteria)
        {
            case COST:
                return cost;
            case DELAY:
                return delay;
            case DISTANCE:
                return distance;
            case CANCELED:
                return canceled;
            case TIME:
                return time;

            default:
                return 0.0;
        }
    }
    
    /**
     * Returns a string representation of origin and destination
     */

    @Override
    public String toString ()
    {
        return this.getOrigin() + "--->" + this.getDestination();
    }
}
