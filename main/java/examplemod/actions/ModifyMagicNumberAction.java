package examplemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.Iterator;
import java.util.UUID;

public class ModifyMagicNumberAction extends AbstractGameAction {
    UUID uuid;

    public ModifyMagicNumberAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            c.baseMagicNumber += this.amount;
            if (c.baseDamage < 0) {
                c.baseDamage = 0;
            }
        }

        this.isDone = true;
    }
}