/**
 * Created by Alexander on 27.11.15.
 */
public class Command {

  private  CommandType type;
    public Command(){

    }

    public Command(CommandType type){
           this.type=type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public enum CommandType{
        ACCEPT{
            @Override
            public String toString() {
                return ("Accepted");
            }
        },

        DISCONNECT{
            @Override
            public String toString() {
                return("Disconnect");
            }
        },

        REJECT{
            @Override
            public String toString() {
                return("Rejected");
            }
        }
    };
}
