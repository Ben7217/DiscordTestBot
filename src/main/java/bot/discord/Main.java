package bot.discord;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.events.user.UserTypingEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Main extends ListenerAdapter {

    public static JDA jda;

    public static void main(String[] args) {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken("NDA2MzAxNTczMDk5MDI4NDgw.DUw9Vw.49fdOqcxQ-ItioxpQI5bRKDh0yc");
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        try {
            jda = builder.buildBlocking();

        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        jda.getPresence().setGame(Game.of(Game.GameType.DEFAULT, "I am a bot, ask me something!"));
        jda.isAudioEnabled();
        jda.addEventListener(new Main());

        jda.asBot().getJDA();
        jda.getRoles();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
        Message objMsg = messageReceivedEvent.getMessage();
        MessageChannel objChannel = messageReceivedEvent.getChannel();
        User objUser = messageReceivedEvent.getAuthor();


        Date date = new Date();
        ArrayList<String> badWords = new ArrayList<>();

        badWords.add("Shit");
        badWords.add("Fuck");
        badWords.add("shit");
        badWords.add("fuck");


        ArrayList<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");


        if (objMsg.getContentDisplay().equalsIgnoreCase("hello")) {
            objChannel.sendMessage("Hello, " + objUser.getAsMention() + "!").queue();
        }

        if (objMsg.getContentDisplay().contains("date")) {
            objChannel.sendMessage("Today is " + date.toString() + ". Anything else pitiful human?").queue();
        }
        for (String badWord : badWords) {
            if (objMsg.getContentDisplay().contains(badWord)) {
                objChannel.sendMessage("Your words hurt me!!! Why are humans SO nasty?!").queue();
            }
        }
        if (objMsg.getContentDisplay().contains("What is")) {
            String firstNumber = objMsg.getContentDisplay().substring(8, 9);
            String secondNumber = objMsg.getContentDisplay().substring(12, 13);

            int sum = Integer.valueOf(firstNumber) + Integer.valueOf(secondNumber);
            int remainder = Integer.valueOf(firstNumber) - Integer.valueOf(secondNumber);
            int product = Integer.valueOf(firstNumber) * Integer.valueOf(secondNumber);
            int divide = Integer.valueOf(firstNumber) / Integer.valueOf(secondNumber);

            if(objMsg.getContentDisplay().contains("+")) {
                objChannel.sendMessage("The sum of " + firstNumber + " + " + secondNumber +
                        " is: " + sum + ".").queue();
            }
            if(objMsg.getContentDisplay().contains("-")) {
                objChannel.sendMessage("The remainder of " + firstNumber + " - " + secondNumber +
                        " is: " + remainder + ".").queue();
            }
            if(objMsg.getContentDisplay().contains("*")) {
                objChannel.sendMessage("The product of " + firstNumber + " * " + secondNumber +
                        " is: " + product + ".").queue();
            }
            if(objMsg.getContentDisplay().contains("/")) {
                objChannel.sendMessage("The quotient of " + firstNumber + " / " + secondNumber +
                        " is: " + divide + ".").queue();
            }
        }



    }

    @Override
    public void onUserTyping(UserTypingEvent event) {
        MessageChannel objChannel = event.getChannel();
        User objUser = event.getUser();

        ArrayList<String> userTypingMessages = new ArrayList<>();
        userTypingMessages.add("Oh look, " + objUser.getName() + " is typing... I can't WAIT to see what it is...");
        userTypingMessages.add("Let's see what kind of brainbuster " + objUser.getName() + " can come up with this time.");
        userTypingMessages.add("If " + objUser.getName() + " says what I think he is about to say, I will lose my damn mind!");
        userTypingMessages.add("Alright, fine. I give up. " + objUser.getName() + " really just wont stop typing.");

        Collections.shuffle(userTypingMessages);
            objChannel.sendMessage(userTypingMessages.get(0)).queue();

    }



}
