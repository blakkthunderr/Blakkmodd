package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class MassiveSlam
        extends CustomCard {
    public static final String ID = "MassiveSlam";
    public static final String NAME = "Massive Slam";
    public static final String DESCRIPTION = "Deal damage equal to the amount of cards in your Draw Pile.";
    public static final String IMG_PATH = "img/MassiveSlam.png";
    private static final int COST = 1;


    public MassiveSlam() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.damage = this.baseDamage;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));

    }

    @Override
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.drawPile.size();

    }

    @Override
    public AbstractCard makeCopy() {
        return new MassiveSlam();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
