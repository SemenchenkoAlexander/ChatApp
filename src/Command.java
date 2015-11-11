
public class Command {

    protected CommandType type;

    public Command() {
    }

    public Command(CommandType t)
    {
        type = t;
    }
    public static enum CommandType
    {
        ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;
    }
}
