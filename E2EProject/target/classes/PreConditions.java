package resources;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
public class PreConditions {
	public boolean civilid_check(String civil)
	{
		boolean id=civil.startsWith("2");
		return id;
	}
	public boolean date_check(String dobyear,String dobmonth,String dobdate,String Option)
	{
		
		boolean status = false;
		String month;
		switch (dobmonth) {
		case "Jan": month="01";
		break;
		case "Feb": month="02";
		break;
		case "March": month="03";
		break;
		case "April": month="04";
		break;
		case "May": month="05";
		break;
		case "June": month="06";
		break;
		case "July": month="07";
		break;
		case "Aug": month="08";
		break;
		case "Sep": month="09";
		break;
		case "Oct": month="10";
		break;
		case "Nov": month="11";
		break;
		case "Dec": month="12";
		break;
		default:
			 month="0";
			break;
		}
		LocalDate currentDate = LocalDate.now();
		 String[] dates=String.valueOf(currentDate).split("-");
		
		 if(Option.equalsIgnoreCase("dob"))
		 {
			 if((Integer.valueOf(dobyear).compareTo(Integer.valueOf(dates[0])-18))<0 && dobyear.startsWith("1"))
				 status=true;
			 else if(Integer.valueOf(dobyear).compareTo((Integer.valueOf(dates[0])-18))==0 && Integer.valueOf(month).compareTo(Integer.valueOf(dates[1]))<=0)
			 {
				 if((Integer.valueOf(month).compareTo(Integer.valueOf(dates[1])))==0&&(Integer.valueOf(dobdate).compareTo(Integer.valueOf(dates[2])))<=0)
					 status= true;
				 else if((Integer.valueOf(month).compareTo(Integer.valueOf(dates[1])))<0)
					 status= true;
					 else
						 status= false;
			 }
			 else
				 status= false;
		 }
		 
		 if(Option.equalsIgnoreCase("gov"))
		 {
			
				 if(Integer.valueOf(dobyear).compareTo(Integer.valueOf(dates[0]))>0 &&  dobyear.startsWith("2"))
					 status= true;
				 else if ((Integer.valueOf(dobyear).compareTo(Integer.valueOf(dates[0]))==0)&&(Integer.valueOf(month).compareTo(Integer.valueOf(dates[1])))>=0 )
				 {
					 
					 if(((Integer.valueOf(month).compareTo(Integer.valueOf(dates[1])))==0)&&(Integer.valueOf(dobdate).compareTo(Integer.valueOf(dates[2])))>=0)
						 status= true;
					 else if((Integer.valueOf(month).compareTo(Integer.valueOf(dates[1])))>0)
						 status= true;
					 else
						 status= false;
				 }
				 else
					 status= false;
		 }
		
		return status;
	}
	public int  month_check(String dobmonth)
	{
		 switch (dobmonth) {
         case "Jan":
         case "January":
             return 1;
         case "Feb":
         case "February":
             return 2;
         case "Mar":
         case "March":
             return 3;
         case "Apr":
         case "April":
             return 4;
         case "May":
             return 5;
         case "Jun":
         case "June":
             return 6;
         case "Jul":
         case "July":
             return 7;
         case "Aug":
         case "August":
             return 8;
         case "Sep":
         case "September":
             return 9;
         case "Oct":
         case "October":
             return 10;
         case "Nov":
         case "November":
             return 11;
         case "Dec":
         case "December":
             return 12;
         default:
             return -1; // Indicates month name not found
     }
	}
	public int YearNum(String DobYear,String Option)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Calendar c=Calendar.getInstance();
	String govYear=sdf.format(c.getTime()).substring(2,DobYear.length());
		int num;
		if(!DobYear.startsWith("2"))
		{
		DobYear=DobYear.substring(2,DobYear.length());
		num=Integer.parseInt(DobYear);
		}
		else
		{
			if(Option.equalsIgnoreCase("gov"))
			{
				DobYear=DobYear.substring(2,DobYear.length());
				num=Integer.parseInt(DobYear);
				num=num-Integer.parseInt(govYear);
			}
			else
			{
			DobYear=DobYear.substring(2,DobYear.length());
			num=Integer.parseInt(DobYear)+100;}
		}
		return num;
	}
	
	public int button(String option)
	{
		System.out.println("Pre condition "+option);
	
		if(option.equalsIgnoreCase("gov"))
		{
			return 1;
		}
		else if(option.equalsIgnoreCase("dob"))
		{
			return -1;
		}
		else
			return 0;
	}
}
