/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Model.*;
import View.*;
import Controller.TrackAndFieldController;

/**
 *
 * @author DM3-1-03
 */
public class TrackAndField
{
    // Define the model members
    Athlete athModel;
    Coach chModel;
    Competition compModel;
    Discipline disModel;
    // Person perModel;
    Registration regModel;
    Result rsModel;
    Schedule schModel;
    Team tmModel;
    private AthleteMethods athMeth;
    private CoachMethods chMeth;
    private CompetitionMethods compMeth; // = new CompetitionDataAccess();
    private DisciplineMethods disMeth;
    private RegistrationMethods regMeth;
    private ResultMethods rsMeth;
    private ScheduleMethods schMeth;
    private TeamMethods tmMeth;
    private PasswordGenerator passwdGen;
    private SendMailMethods sendMailMeth;
    
    // Define the view members
    private login loginMenu;
    private newAccount newAccView;
    private accountRecovery accRecovery;
    private mainPage mainPage;
    private mainPageGuest mpGuest;
    private addChangeAthlete addAthView;
    private addChangeCoach addChView;
    private addChangeCompetition addCompView;
    private addChangeDiscipline addDisView;
    private addChangeRegistration addRegView;
    private addChangeResult addRsView;
    private addChangeSchedule addSchView;
    private addChangeTeam addTmView;
    private manageAthlete mgAthView;
    private manageCoach mgChView;
    private manageCompetition mgCompView;
    private manageDiscipline mgDisView;
    private manageRegistration mgRegView;
    private manageResult mgRsView;
    private manageSchedule mgSchView;
    private manageTeam mgTmView;
    private showOneAth shOneAth;
    private showOneCh shOneCh;
    private showOneComp shOneComp;
    private showOneDis shOneDis;
    private showOneReg shOneReg;
    private showOneRs shOneRs;
    private showOneSch shOneSch;
    private showOneTm shOneTm;
    
    // Define the controller
    TrackAndFieldController ctrl;
    
    public static void main(String[] args)
    {
        TrackAndField tfApp = new TrackAndField();
        tfApp.start();
    }
    
    private void start()
    {
        // Initialize the model members
        athModel = new Athlete(false);
        chModel = new Coach(false);
        compModel = new Competition(false);
        disModel = new Discipline(false);
        // perModel = new Person(false);
        regModel = new Registration(false);
        rsModel = new Result(false);
        schModel = new Schedule(false);
        tmModel = new Team(false);
        athMeth = new AthleteMethods();
        chMeth = new CoachMethods();
        compMeth = new CompetitionMethods();
        disMeth = new DisciplineMethods();
        regMeth = new RegistrationMethods();
        rsMeth = new ResultMethods();
        schMeth = new ScheduleMethods();
        tmMeth = new TeamMethods();
        passwdGen = new PasswordGenerator();
        sendMailMeth = new SendMailMethods();
        
        // Initialize the view members
        loginMenu = new login();
        newAccView = new newAccount();
        accRecovery = new accountRecovery();
        mainPage = new mainPage();
        mpGuest = new mainPageGuest();
        addAthView = new addChangeAthlete();
        addChView = new addChangeCoach();
        addCompView = new addChangeCompetition();
        addDisView = new addChangeDiscipline();
        addRegView = new addChangeRegistration();
        addRsView = new addChangeResult();
        addSchView = new addChangeSchedule();
        addTmView = new addChangeTeam();
        mgAthView = new manageAthlete();
        mgChView = new manageCoach();
        mgCompView = new manageCompetition();
        mgDisView = new manageDiscipline();
        mgRegView = new manageRegistration();
        mgRsView = new manageResult();
        mgSchView = new manageSchedule();
        mgTmView = new manageTeam();
        shOneAth = new showOneAth();
        shOneCh = new showOneCh();
        shOneComp = new showOneComp();
        shOneDis = new showOneDis();
        shOneReg = new showOneReg();
        shOneRs = new showOneRs();
        shOneSch = new showOneSch();
        shOneTm = new showOneTm();
        
        ctrl = new TrackAndFieldController(athModel, chModel, compModel, disModel, /*perModel,*/ regModel, rsModel, schModel, tmModel, 
        athMeth, chMeth, compMeth, disMeth, regMeth, rsMeth, schMeth, tmMeth, passwdGen, sendMailMeth, loginMenu, newAccView, accRecovery, mainPage, 
        mpGuest, addAthView, addChView, addCompView, addDisView, addRegView, addRsView, addSchView, addTmView, mgAthView, mgChView, mgCompView, 
        mgDisView, mgRegView, mgRsView, mgSchView, mgTmView, shOneAth, shOneCh, shOneComp, shOneDis, shOneReg, shOneRs, shOneSch, shOneTm);
        
        loginMenu.setVisible(true);
    }
}
