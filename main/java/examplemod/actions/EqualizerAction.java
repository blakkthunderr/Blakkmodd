package examplemod.actions;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class EqualizerAction extends AbstractGameAction {
    private AbstractPlayer p;


    public EqualizerAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Iterator var1 = this.p.drawPile.group.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();


                c.cost = 1;
                c.isCostModified = true;

            }

            Iterator var2 = this.p.discardPile.group.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();


                c.cost = 1;
                c.isCostModified = true;

            }
        }

        this.tickDuration();
    }
}
