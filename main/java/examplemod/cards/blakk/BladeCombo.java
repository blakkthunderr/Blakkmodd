package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

import java.util.Iterator;

public class BladeCombo
        extends CustomCard {
    public static final String ID = "BladeCombo";
    public static final String NAME = "Blade Combo";
    public static final String DESCRIPTION = "Deal !D! damage for every card you played this turn.";
    public static final String IMG_PATH = "img/BladeCombo.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 2;


    public BladeCombo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();
        int count = 0;
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();

                ++count;

        }

        --count;

        for(int i = 0; i < count; ++i) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new BladeCombo();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);

        }
    }
}
