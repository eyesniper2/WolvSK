package fr.nashoba24.wolvsk.twitter;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import twitter4j.Status;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondIsRetweetedByMe extends Condition {
	
	private Expression<Status> status;

    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kl, ParseResult pr) {
    	status = (Expression<Status>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "is retweeted by me";
    }

    @Override
    public boolean check(Event e) {
    	if(WolvSKTwitter.tf==null) { return false; }
    	return status.getSingle(e).isRetweetedByMe();
    }

}