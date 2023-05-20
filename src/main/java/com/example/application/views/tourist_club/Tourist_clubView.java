package com.example.application.views.tourist_club;

import com.example.application.backend.models.*;
import com.example.application.backend.services.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Просмотр и редактирование данных системы")
@Route(value = "tourist_club", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class Tourist_clubView extends Div {
    public Tourist_clubView(TouristService touristService, CompetitionsService competitionsService, SectionsService sectionsService,
                            GroupsService groupsService, HikesService hikesService, HikersService hikersService,
                            RoutesService routesService, TouristsToGroupsService touristsToGroupsService, TrainingsService trainingsService,
                            RoutePointsService routePointsService, CompetitorsService competitorsService, LeadersService leadersService,
                            CampsAndStopsService campsAndStopsService, CoachesService coachesService, HikeDiariesService hikeDiariesService,
                            TouristTypesService touristTypesService, TypesService typesService, VisitorsService visitorsService,
                            PointsService pointsService) {
        addClassName("touristclub-view");

        Accordion accordion = new Accordion();

        VerticalLayout touristFormLayout = new VerticalLayout();
        VerticalLayout sectionsFormLayout = new VerticalLayout();
        VerticalLayout competitionsFormLayout = new VerticalLayout();
        VerticalLayout groupsFormLayout = new VerticalLayout();
        VerticalLayout hikesFormLayout = new VerticalLayout();
        VerticalLayout hikersFormLayout = new VerticalLayout();
        VerticalLayout routesFormLayout = new VerticalLayout();
        VerticalLayout touristsToGroupsFormLayout = new VerticalLayout();
        VerticalLayout trainingsFormLayout = new VerticalLayout();
        VerticalLayout pointsFormLayout = new VerticalLayout();
        VerticalLayout routesPointsFormLayout = new VerticalLayout();
        VerticalLayout competitorsFormLayout = new VerticalLayout();
        VerticalLayout leadersFormLayout = new VerticalLayout();
        VerticalLayout campsAndStopsLayout = new VerticalLayout();
        VerticalLayout coachesLayout = new VerticalLayout();
        VerticalLayout hikeDiariesLayout = new VerticalLayout();
        VerticalLayout touristTypesLayout = new VerticalLayout();
        VerticalLayout typesLayout = new VerticalLayout();
        VerticalLayout visitorsLayout = new VerticalLayout();

        AccordionPanel touristsPanel = accordion.add("Туристы", touristFormLayout);
        AccordionPanel sectionsPanel = accordion.add("Секции", sectionsFormLayout);
        AccordionPanel competitionsPanel = accordion.add("Соревнования", competitionsFormLayout);
        AccordionPanel groupsPanel = accordion.add("Группы", groupsFormLayout);
        AccordionPanel hikesPanel = accordion.add("Походы", hikesFormLayout);
        AccordionPanel hikersPanel = accordion.add("Хайкеры", hikersFormLayout);
        AccordionPanel routesPanel = accordion.add("Маршруты", routesFormLayout);
        AccordionPanel touristsToGroupsPanel = accordion.add("Туристы в группах", touristsToGroupsFormLayout);
        AccordionPanel trainingsPanel = accordion.add("Тренировки", trainingsFormLayout);
        AccordionPanel pointsPanel = accordion.add("Точки маршрутов", pointsFormLayout);
        AccordionPanel routesPointsPanel = accordion.add("Точки в маршрутах", routesPointsFormLayout);
        AccordionPanel competitorsPanel = accordion.add("Участники соревнований", competitorsFormLayout);
        AccordionPanel leadersPanel = accordion.add("Лидеры секций", leadersFormLayout);
        AccordionPanel campsStopsPanel = accordion.add("Привалы и стоянки", campsAndStopsLayout);
        AccordionPanel coachesPanel = accordion.add("Тренеры", coachesLayout);
        AccordionPanel hikeDiariesPanel = accordion.add("Дневники походов", hikeDiariesLayout);
        AccordionPanel touristTypesPanel = accordion.add("Типы туристов", touristTypesLayout);
        AccordionPanel typesPanel = accordion.add("Типы занятий в клубе", typesLayout);
        AccordionPanel visitorsPanel = accordion.add("Посетители тренировок", visitorsLayout);

        var crud = new GridCrud<>(Tourists.class, touristService);
        crud.setWidthFull();
        crud.getGrid().setColumns("name", "sex", "birthday", "category");
        crud.getCrudFormFactory().setVisibleProperties("name", "birthday", "sex", "type", "category", "notes");
        TextField filter1 = new TextField();
        filter1.setPlaceholder("Поиск по имени");
        filter1.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter1);
        crud.setOperations(
                () -> touristService.findByNameContainingIgnoreCase(filter1.getValue()),
                user -> touristService.add(user),
                user -> touristService.update(user),
                user -> touristService.delete(user)
        );
        filter1.addValueChangeListener(e -> crud.refreshGrid());
        crud.getGrid().addColumn(t -> touristService.getTypeName(t.getType())).setHeader("Tourist type").setKey("type_name");
        crud.getGrid().addColumn(t -> t.getNotes()).setHeader("Notes").setKey("t_notes");
        crud.getCrudFormFactory().setFieldProvider("type", t -> {
            TextField tourField = touristsTypesField(touristTypesService);
            return tourField;
        });
        crud.getCrudFormFactory().setShowNotifications(true);
        crud.getCrudFormFactory().setUseBeanValidation(true);

        touristFormLayout.add(crud);

        var crudSec = new GridCrud<>(Sections.class, sectionsService);
        crudSec.setWidthFull();
        crudSec.getGrid().setColumns("name");
        crudSec.getCrudFormFactory().setVisibleProperties("name", "type", "leader");
        TextField filter2 = new TextField();
        filter2.setPlaceholder("Поиск по названию");
        filter2.setClearButtonVisible(true);
        crudSec.getCrudLayout().addFilterComponent(filter2);
        crudSec.setOperations(
                () -> sectionsService.findByNameContainingIgnoreCase(filter2.getValue()),
                user -> sectionsService.add(user),
                user -> sectionsService.update(user),
                user -> sectionsService.delete(user)
        );
        filter2.addValueChangeListener(e -> crudSec.refreshGrid());
        crudSec.getGrid().addColumn(s -> sectionsService.getTypeName(s.getType())).setHeader("Section type").setKey("type_name");
        crudSec.getGrid().addColumn(s -> sectionsService.getLeaderName(s.getLeader())).setHeader("Leader").setKey("leader_name");
        crudSec.getCrudFormFactory().setFieldProvider("leader", leader -> {
            TextField field = new TextField();
            Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
            VerticalLayout verticalLayout = new VerticalLayout();
            ComboBox<String> leadersNames = new ComboBox<>("Имена лидеров");
            leadersNames.setItems(leadersService.findNamed());
            leadersNames.setAllowCustomValue(false);
            leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
                field.setValue(String.valueOf(leadersService.findIdByName(leadersNames.getValue())));
            });
            verticalLayout.add(leadersNames);
            Dialog dialog = new Dialog(verticalLayout);
            dialog.add(verticalLayout);
            button.addClickListener(buttonClickEvent -> {
                dialog.open();
            });
            field.setSuffixComponent(button);
            return field;
        });
        crudSec.getCrudFormFactory().setFieldProvider("type", type -> {
            TextField textField = typesField(typesService);
            return textField;
        });
        crudSec.getCrudFormFactory().setUseBeanValidation(true);
        crudSec.getCrudFormFactory().setShowNotifications(true);

        sectionsFormLayout.add(crudSec);

        var crudC = new GridCrud<>(Competitions.class, competitionsService);
        crudC.setWidthFull();
        crudC.getCrudFormFactory().setVisibleProperties("name");
        TextField filter3 = new TextField();
        filter3.setPlaceholder("Поиск по названию");
        filter3.setClearButtonVisible(true);
        crudC.getCrudLayout().addFilterComponent(filter3);
        crudC.setOperations(
                () -> competitionsService.findByNameContainingIgnoreCase(filter3.getValue()),
                user -> competitionsService.add(user),
                user -> competitionsService.update(user),
                user -> competitionsService.delete(user)
        );
        filter3.addValueChangeListener(e -> crudC.refreshGrid());
        crudC.getGrid().setColumns("name");
        crudC.getCrudFormFactory().setUseBeanValidation(true);
        crudC.getCrudFormFactory().setShowNotifications(true);

        competitionsFormLayout.add(crudC);

        var crudG = new GridCrud<>(Groups.class, groupsService);
        crudG.setWidthFull();
        crudG.getGrid().setColumns();
        crudG.getCrudFormFactory().setVisibleProperties("section", "coach");
        crudG.getGrid().addColumn(g -> g.getId()).setHeader("Number").setKey("g_id");
        crudG.getGrid().addColumn(s -> sectionsService.getSectionName(s.getSection())).setHeader("Section").setKey("section_name");
        crudG.getGrid().addColumn(s -> touristService.getNameById(s.getCoach())).setHeader("Coach").setKey("coach_name");
        crudG.getCrudFormFactory().setFieldProvider("section", s -> {
            TextField gsField = sectionsField(sectionsService);
            return gsField;
        });
        crudG.getCrudFormFactory().setFieldProvider("coach", c -> {
            TextField gcField = coachField(coachesService, touristService);
            return gcField;
        });
        crudG.getCrudFormFactory().setUseBeanValidation(true);
        crudG.getCrudFormFactory().setShowNotifications(true);

        groupsFormLayout.add(crudG);

        var crudH = new GridCrud<>(Hikes.class, hikesService);
        crudH.setWidthFull();
        crudH.getGrid().setColumns("id", "complexity", "planned", "start_date");
        crudH.getCrudFormFactory().setVisibleProperties("route", "instructor", "type",
                "complexity", "planned", "start_date");
        crudH.getGrid().addColumn(s -> routesService.getRouteName(s.getRoute())).setHeader("Route").setKey("route_name");
        crudH.getGrid().addColumn(s -> touristService.getNameById(s.getInstructor())).setHeader("Instructor").setKey("instructor_name");
        crudH.getGrid().addColumn(s -> sectionsService.getTypeName(s.getType())).setHeader("Type").setKey("type_name");
        crudH.getCrudFormFactory().setFieldProvider("route", r -> {
            TextField hrField = routeField(routesService);
            return hrField;
        });
        crudH.getCrudFormFactory().setFieldProvider("type", t -> {
            TextField htField = typesField(typesService);
            return htField;
        });
        crudH.getCrudFormFactory().setFieldProvider("instructor", i -> {
            TextField hiField = notLoverField(touristService);
            return hiField;
        });
        crudH.getCrudFormFactory().setUseBeanValidation(true);
        crudH.getCrudFormFactory().setShowNotifications(true);

        hikesFormLayout.add(crudH);

        var crudHiker = new GridCrud<>(Hikers.class, hikersService);
        crudHiker.setWidthFull();
        crudHiker.getGrid().setColumns("hike");
        crudHiker.getGrid().addColumn(s -> touristService.getNameById(s.getHiker())).setHeader("Hiker").setKey("hiker_name");
        crudHiker.getCrudFormFactory().setFieldProvider("hiker", h -> {
            TextField hhField = touristField(touristService);
            return hhField;
        });
        crudHiker.getCrudFormFactory().setFieldProvider("hike", h -> {
            TextField hField = hikeField(hikesService);
            return hField;
        });
        crudHiker.getCrudFormFactory().setUseBeanValidation(true);
        crudHiker.getCrudFormFactory().setShowNotifications(true);

        hikersFormLayout.add(crudHiker);

        var crudR = new GridCrud<>(Routes.class, routesService);
        crudR.setWidthFull();
        crudR.getGrid().setColumns("name", "days", "length");
        crudR.getCrudFormFactory().setVisibleProperties("name", "days");
        crudR.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "name", "days", "length");
        TextField filter4 = new TextField();
        filter4.setPlaceholder("Поиск по названию");
        filter4.setClearButtonVisible(true);
        crudR.getCrudLayout().addFilterComponent(filter4);
        crudR.setOperations(
                () -> routesService.findByNameContainingIgnoreCase(filter4.getValue()),
                user -> routesService.add(user),
                user -> routesService.update(user),
                user -> routesService.delete(user)
        );
        filter4.addValueChangeListener(e -> crudR.refreshGrid());
        crudR.getCrudFormFactory().setUseBeanValidation(true);
        crudR.getCrudFormFactory().setShowNotifications(true);

        routesFormLayout.add(crudR);

        var crudTG = new GridCrud<>(TouristsToGroups.class, touristsToGroupsService);
        crudTG.setWidthFull();
        crudTG.getGrid().setColumns();
        crudTG.getGrid().addColumn(g -> g.getGrooup()).setHeader("Group").setKey("group_num");
        crudTG.getCrudFormFactory().setVisibleProperties("tourist", "grooup");
        crudTG.getGrid().addColumn(s -> touristService.getNameById(s.getTourist())).setHeader("Tourist").setKey("tourist_name");
        crudTG.getCrudFormFactory().setFieldProvider("tourist", t -> {
            TextField tgField = touristField(touristService);
            return tgField;
        });
        crudTG.getCrudFormFactory().setFieldProvider("grooup", g -> {
            TextField tggField = groupField(groupsService);
            tggField.setLabel("Group");
            return tggField;
        });
        crudTG.getCrudFormFactory().setUseBeanValidation(true);
        crudTG.getCrudFormFactory().setShowNotifications(true);

        touristsToGroupsFormLayout.add(crudTG);

        var crudTr = new GridCrud<>(Trainings.class, trainingsService);
        crudTr.setWidthFull();
        crudTr.getGrid().setColumns("name", "date", "grooup", "place", "start_time", "duration");
        crudTr.getCrudFormFactory().setVisibleProperties("name", "date", "grooup", "place", "start_time", "duration", "description");
        TextField filter5 = new TextField();
        filter5.setPlaceholder("Поиск по названию");
        filter5.setClearButtonVisible(true);
        crudTr.getCrudLayout().addFilterComponent(filter5);
        crudTr.setOperations(
                () -> trainingsService.findByNameContainingIgnoreCase(filter5.getValue()),
                user -> trainingsService.add(user),
                user -> trainingsService.update(user),
                user -> trainingsService.delete(user)
        );
        crudTr.getCrudFormFactory().setFieldProvider("grooup", g -> {
            TextField tggField = groupField(groupsService);
            tggField.setLabel("Group");
            return tggField;
        });
        filter5.addValueChangeListener(e -> crudTr.refreshGrid());
        crudTr.getGrid().getColumnByKey("grooup").setHeader("Group");
        crudTr.getCrudFormFactory().setFieldType("start_time", TimePicker.class);
        crudTr.getCrudFormFactory().setUseBeanValidation(true);
        crudTr.getCrudFormFactory().setShowNotifications(true);

        trainingsFormLayout.add(crudTr);

        var crudP = new GridCrud<>(Points.class, pointsService);
        crudP.setWidthFull();
        crudP.getGrid().setColumns("name", "description");
        crudP.getCrudFormFactory().setVisibleProperties("name", "coordinates", "description");
        crudP.getAddButton().setEnabled(false);
        TextField filter7 = new TextField();
        filter7.setPlaceholder("Поиск по названию");
        filter7.setClearButtonVisible(true);
        crudP.getCrudLayout().addFilterComponent(filter7);
        crudP.setOperations(
                () -> pointsService.findByNameContainingIgnoreCase(filter7.getValue()),
                user -> pointsService.add(user),
                user -> pointsService.update(user),
                user -> pointsService.delete(user)
        );
        filter7.addValueChangeListener(e -> crudP.refreshGrid());
        crudP.getCrudFormFactory().setUseBeanValidation(true);
        crudP.getCrudFormFactory().setShowNotifications(true);

        pointsFormLayout.add(crudP);

        var crudRP = new GridCrud<>(RoutePoints.class, routePointsService);
        crudRP.getGrid().setColumns();
        crudRP.setWidthFull();
        crudRP.getGrid().addColumn(s -> routesService.getRouteName(s.getRoute())).setHeader("Route").setKey("route_name");
        crudRP.getGrid().addColumn(s -> pointsService.getPointName(s.getPoint())).setHeader("Point").setKey("point_name");
        crudRP.getGrid().addColumn(s -> s.getDay()).setHeader("Day").setKey("day_n");
        crudRP.getCrudFormFactory().setFieldProvider("point", p -> {
            TextField rppField = pointField(pointsService);
            return rppField;
        });
        crudRP.getCrudFormFactory().setFieldProvider("route", r -> {
            TextField rprField = routeField(routesService);
            return rprField;
        });
        crudRP.getCrudFormFactory().setUseBeanValidation(true);
        crudRP.getCrudFormFactory().setShowNotifications(true);

        routesPointsFormLayout.add(crudRP);

        var crudCmp = new GridCrud<>(Competitors.class, competitorsService);
        crudCmp.setWidthFull();
        crudCmp.getGrid().setColumns();
        crudCmp.getGrid().addColumn(s -> competitionsService.getNameById(s.getCompetition())).setHeader("Competition").setKey("competition_name");
        crudCmp.getGrid().addColumn(s -> touristService.getNameById(s.getCompetitor())).setHeader("Competitor").setKey("competitor_name");
        crudCmp.getCrudFormFactory().setFieldProvider("competition", c -> {
            TextField cmpField = competitionField(competitionsService);
            return cmpField;
        });
        crudCmp.getCrudFormFactory().setFieldProvider("competitor", c -> {
            TextField cField = notLoverField(touristService);
            return cField;
        });
        crudCmp.getCrudFormFactory().setUseBeanValidation(true);
        crudCmp.getCrudFormFactory().setShowNotifications(true);

        competitorsFormLayout.add(crudCmp);

        var crudL = new GridCrud<>(Leaders.class, leadersService);
        crudL.setWidthFull();
        crudL.getGrid().setColumns("name", "salary", "employment_year", "birthday");
        crudL.getCrudFormFactory().setVisibleProperties("name", "salary", "employment_year", "birthday");
        TextField filter6 = new TextField();
        filter6.setPlaceholder("Поиск по имени");
        filter6.setClearButtonVisible(true);
        crudL.getCrudLayout().addFilterComponent(filter6);
        crudL.setOperations(
                () -> leadersService.findByNameContainingIgnoreCase(filter6.getValue()),
                user -> leadersService.add(user),
                user -> leadersService.update(user),
                user -> leadersService.delete(user)
        );
        filter6.addValueChangeListener(e -> crudL.refreshGrid());
        crudL.getCrudFormFactory().setUseBeanValidation(true);
        crudL.getCrudFormFactory().setShowNotifications(true);

        leadersFormLayout.add(crudL);

        var crudCS = new GridCrud<>(CampsAndStops.class, campsAndStopsService);
        crudCS.setWidthFull();
        crudCS.getGrid().setColumns("hike", "day", "camps", "stops");
        crudCS.getCrudFormFactory().setUseBeanValidation(true);
        crudCS.getCrudFormFactory().setShowNotifications(true);

        campsAndStopsLayout.add(crudCS);

        var crudCoaches= new GridCrud<>(Coaches.class, coachesService);
        crudCoaches.setWidthFull();
        crudCoaches.getGrid().setColumns();
        crudCoaches.getCrudFormFactory().setVisibleProperties("section", "salary");
        crudCoaches.getAddButton().setEnabled(false);
        crudCoaches.getGrid().addColumn(s -> touristService.getNameById(s.getId())).setHeader("Coach").setKey("coach_name");
        crudCoaches.getGrid().addColumn(s -> sectionsService.getSectionName(s.getSection())).setHeader("Section").setKey("section_name");
        crudCoaches.getGrid().addColumn(s -> s.getSalary()).setHeader("Salary").setKey("coach_salary");
        crudCoaches.getCrudFormFactory().setFieldProvider("section", s -> {
            TextField sField = sectionsField(sectionsService);
            return  sField;
        });
        crudCoaches.getCrudFormFactory().setUseBeanValidation(true);
        crudCoaches.getCrudFormFactory().setShowNotifications(true);

        coachesLayout.add(crudCoaches);

        var crudHD = new GridCrud<>(HikeDiaries.class, hikeDiariesService);
        crudHD.setWidthFull();
        crudHD.getGrid().setColumns("hike", "day", "notes");
        crudHD.getCrudFormFactory().setUseBeanValidation(true);
        crudHD.getCrudFormFactory().setShowNotifications(true);

        hikeDiariesLayout.add(crudHD);

        var crudTT = new GridCrud<>(TouristTypes.class, touristTypesService);
        crudTT.setWidthFull();
        crudTT.getCrudFormFactory().setVisibleProperties("name");
        crudTT.getCrudFormFactory().setUseBeanValidation(true);
        crudTT.getCrudFormFactory().setShowNotifications(true);
        crudTT.getUpdateButton().setEnabled(false);
        crudTT.getAddButton().setEnabled(false);

        touristTypesLayout.add(crudTT);

        var crudT = new GridCrud<>(Types.class, typesService);
        crudT.setWidthFull();
        crudT.getCrudFormFactory().setVisibleProperties("name");
        crudT.getCrudFormFactory().setUseBeanValidation(true);
        crudT.getCrudFormFactory().setShowNotifications(true);
        crudT.getAddButton().setEnabled(false);
        crudT.getUpdateButton().setEnabled(false);

        typesLayout.add(crudT);

        var crudV = new GridCrud<>(Visitors.class, visitorsService);
        crudV.setWidthFull();
        crudV.getGrid().setColumns();
        crudV.getGrid().addColumn(s -> touristService.getNameById(s.getVisitor())).setHeader("Visitor").setKey("visitor_name");
        crudV.getGrid().addColumn(s -> trainingsService.getNameById(s.getTraining())).setHeader("Training").setKey("training_name");
        crudV.getCrudFormFactory().setFieldProvider("training", t -> {
            TextField trField = trainingField(trainingsService);
            return trField;
        });
        crudV.getCrudFormFactory().setFieldProvider("visitor", v -> {
            TextField vField = touristField(touristService);
            return vField;
        });
        crudV.getCrudFormFactory().setUseBeanValidation(true);
        crudV.getCrudFormFactory().setShowNotifications(true);

        visitorsLayout.add(crudV);

        add(createFormLayout());

        add(accordion);
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        return formLayout;
    }

    TextField typesField(TypesService typesService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия типов");
        leadersNames.setItems(typesService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(typesService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField touristsTypesField(TouristTypesService touristTypesService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия типов");
        leadersNames.setItems(touristTypesService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(touristTypesService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    };

    TextField sectionsField(SectionsService sectionsService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия секций");
        leadersNames.setItems(sectionsService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(sectionsService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField coachField(CoachesService coachesService, TouristService touristService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Имена тренеров");
        leadersNames.setItems(coachesService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(touristService.getIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField routeField(RoutesService routesService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия маршрутов");
        leadersNames.setItems(routesService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(routesService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField instructorField(TouristService touristService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Имена инструкторов");
        leadersNames.setItems(touristService.findNamedInstructors());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(touristService.getIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField touristField(TouristService touristService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Имена туристов");
        leadersNames.setItems(touristService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(touristService.getIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField pointField(PointsService pointsService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия точек");
        leadersNames.setItems(pointsService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(pointsService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField competitionField(CompetitionsService competitionsService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия соревнований");
        leadersNames.setItems(competitionsService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(competitionsService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField notLoverField(TouristService touristService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Имена туристов");
        leadersNames.setItems(touristService.findNamedNotLover());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(touristService.getIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField trainingField(TrainingsService trainingsService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<String> leadersNames = new ComboBox<>("Названия тренировок");
        leadersNames.setItems(trainingsService.findNamed());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(trainingsService.findIdByName(leadersNames.getValue())));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField hikeField(HikesService hikesService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<Integer> leadersNames = new ComboBox<>("Номера походов");
        leadersNames.setItems(hikesService.findIdd());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(leadersNames.getValue()));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }

    TextField groupField(GroupsService groupsService) {
        TextField field = new TextField();
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        VerticalLayout verticalLayout = new VerticalLayout();
        ComboBox<Integer> leadersNames = new ComboBox<>("Номера групп");
        leadersNames.setItems(groupsService.findIdd());
        leadersNames.setAllowCustomValue(false);
        leadersNames.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            field.setValue(String.valueOf(leadersNames.getValue()));
        });
        verticalLayout.add(leadersNames);
        Dialog dialog = new Dialog(verticalLayout);
        dialog.add(verticalLayout);
        button.addClickListener(buttonClickEvent -> {
            dialog.open();
        });
        field.setSuffixComponent(button);
        return field;
    }
}
