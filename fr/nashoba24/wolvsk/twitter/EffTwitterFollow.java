package fr.nashoba24.wolvsk.twitter;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import twitter4j.TwitterException;
import twitter4j.User;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffTwitterFollow extends Effect {
	
	private Expression<User> user;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		user = (Expression<User>) expr[0];
		return true;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean b) {
		return "follow user";
	}
	
	@Override
	protected void execute(Event e) {
		if(WolvSKTwitter.tf==null) { return; }
		try {
			WolvSKTwitter.tf.getInstance().createFriendship(user.getSingle(e).getScreenName());
		} catch (TwitterException e1) {
			e1.printStackTrace();
			System.out.println("Failed to follow: " + e1.getMessage());
		}
	}
}
