package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class Graveyard
        extends CustomCard {
    public static final String ID = "Graveyard";
    public static final String NAME = "Graveyard";
    public static final String DESCRIPTION = "Deal !D! damage to ALL enemies for every Soul you have. Exhaust.";
    public static final String IMG_PATH = "img/GraveYard.png";
    private static final int COST = 3;
    //private static final int BLOCK_AMT = 6;


    public Graveyard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.magicNumber = 0;
        this.damage=this.baseDamage;
        this.isMultiDamage = true;
        this.exhaust = true;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            //AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            int i;

            if (p.hasPower(SoulPower.POWER_ID)) {
                this.magicNumber = (p.getPower(SoulPower.POWER_ID).amount);
            } else {
                this.magicNumber = 0;
            }
            for (i = 0; i < this.magicNumber; ++i) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

            }
        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new Graveyard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);

        }
    }
}
