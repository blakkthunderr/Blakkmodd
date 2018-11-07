package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class BlackenedSword
        extends CustomCard {
    public static final String ID = "BlackenedSword";
    public static final String NAME = "Blackened Sword";
    public static final String DESCRIPTION = "Deal !D! damage. Does + !M! damage for every stack of Weak, Vulnerable, and Frail you have.";
    public static final String IMG_PATH = "img/BlackenedSword.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 12;


    public BlackenedSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.damage=this.baseDamage = ATTACK_DMG;

        //this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

        //this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

        //this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {

        int count = 0;

        if (AbstractDungeon.player.hasPower(WeakPower.POWER_ID)) {
            count += (AbstractDungeon.player.getPower(WeakPower.POWER_ID).amount);
        }
        if (AbstractDungeon.player.hasPower(FrailPower.POWER_ID)) {
            count += (AbstractDungeon.player.getPower(FrailPower.POWER_ID).amount);
        }
        if (AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID)) {
            count += (AbstractDungeon.player.getPower(VulnerablePower.POWER_ID).amount);
        }
        this.baseDamage+=(count*this.magicNumber);

        super.calculateCardDamage(mo);
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));


    }

    @Override
    public AbstractCard makeCopy() {
        return new BlackenedSword();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);


        }
    }
}
