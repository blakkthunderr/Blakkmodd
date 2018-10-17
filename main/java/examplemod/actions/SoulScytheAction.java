package examplemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import examplemod.powers.SoulPower;

public class SoulScytheAction extends AbstractGameAction {
    private AbstractCreature c;
    private DamageInfo info;

    public SoulScytheAction(AbstractCreature target, DamageInfo info, AbstractCreature c) {
        this.info = info;
        this.setValues(target, info);
        this.c = c;
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HEAVY));
            this.target.damage(this.info);
            if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.c, this.c, new SoulPower(this.c, 1), 1));

            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}