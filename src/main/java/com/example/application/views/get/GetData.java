package com.example.application.views.get;

import com.example.application.backend.helpers.CSService;
import com.example.application.backend.helpers.CompetitionSection;
import com.example.application.backend.helpers.Load;
import com.example.application.backend.helpers.LoadService;
import com.example.application.backend.models.*;
import com.example.application.backend.repositories.CompetitionsRepository;
import com.example.application.backend.services.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.vaadin.flow.component.notification.Notification.show;


@PageTitle("Получить данные")
@Route(value = "about", layout = MainLayout.class)
public class GetData extends VerticalLayout {
    public GetData(TouristService touristService, SectionsService sectionsService, CoachesService coachesService,
                   CompetitionsService competitionsService,
                   LeadersService leadersService, RoutesService routesService, GroupsService groupsService,
                   CSService csService, LoadService loadService, TrainingsService trainingsService,
                   HikesService hikesService, PointsService pointsService) {
        addClassName("touristclub-view");

        Accordion accordion = new Accordion();

        VerticalLayout form1 = new VerticalLayout();
        VerticalLayout form2 = new VerticalLayout();
        VerticalLayout form3 = new VerticalLayout();
        VerticalLayout form13 = new VerticalLayout();
        VerticalLayout form5 = new VerticalLayout();
        VerticalLayout form6 = new VerticalLayout();
        VerticalLayout form7 = new VerticalLayout();
        //VerticalLayout form8 = new VerticalLayout();
        VerticalLayout form9 = new VerticalLayout();
        VerticalLayout form10 = new VerticalLayout();
        VerticalLayout form11 = new VerticalLayout();
        VerticalLayout form12 = new VerticalLayout();

        AccordionPanel panel1 = accordion.add("Получить список туристов", form1);
        AccordionPanel panel2 = accordion.add("Получить список тренеров", form2);
        AccordionPanel panel3 = accordion.add("Получить перечень соревнований", form3);
        AccordionPanel panel5 = accordion.add("Получить список туристов по секции или группе", form5);
        AccordionPanel panel6 = accordion.add("Получить перечень руководителей секций", form6);
        AccordionPanel panel7 = accordion.add("Получить нагрузку", form7);
        //AccordionPanel panel8 = accordion.add("Получить перечень маршрутов", form8);
        AccordionPanel panel9 = accordion.add("Получить перечень маршрутов", form9);
        AccordionPanel panel10 = accordion.add("Получить список туристов по секции или группе", form10);
        AccordionPanel panel11 = accordion.add("Получить перечень инструкторов", form11);
        AccordionPanel panel12 = accordion.add("Получить список туpистов из указанной секции, гpуппы, котоpые ходили\n" +
                "в походы со своим тpенеpом в качестве инстpуктоpа", form12);
        AccordionPanel panel13 = accordion.add("Получить список туристов по секции или группе гpуппы, котоpые ходили\n" +
                "по всем маpшpутам, по указанным маpшpутам", form13);

        Button all = new Button("Список туристов");
        ComboBox<String> section = new ComboBox<>("По секции");
        section.setItems(sectionsService.findNamed());
        section.setAllowCustomValue(false);
        section.setWidthFull();
        ComboBox<Integer> group= groupsBox(groupsService);
        ComboBox<String> sex = new ComboBox<>("По полу");
        sex.setItems("ж", "м");
        sex.setAllowCustomValue(false);
        IntegerField year = new IntegerField("По году рождения");
        IntegerField age = new IntegerField("По возрасту");
        TextField textField = new TextField("Всего результатов");
        textField.setReadOnly(true);
        Grid<Tourists> grid1 = new Grid<>(Tourists.class);
        grid1.setColumns("name");

        all.addClickListener(buttonClickEvent -> {
            grid1.setItems(touristService.findAll());
            textField.setValue(String.valueOf(touristService.findAll().size()));
        });
        section.addValueChangeListener(clientValidatedEvent -> {
            grid1.setItems(touristService.findBySection(section.getValue()));
            textField.setValue(String.valueOf(touristService.findBySection(section.getValue()).size()));
        });
        group.addValueChangeListener(clientValidatedEvent -> {
            grid1.setItems(touristService.findByGroup(group.getValue()));
            textField.setValue(String.valueOf(touristService.findByGroup(group.getValue()).size()));
        });
        sex.addValueChangeListener(clientValidatedEvent -> {
            grid1.setItems(touristService.findBySex(sex.getValue()));
            textField.setValue(String.valueOf(touristService.findBySex(sex.getValue()).size()));
        });
        year.addValueChangeListener(clientValidatedEvent -> {
            grid1.setItems(touristService.findByYear(year.getValue()));
            textField.setValue(String.valueOf(touristService.findByYear(year.getValue()).size()));
        });
        age.addValueChangeListener(clientValidatedEvent -> {
            grid1.setItems(touristService.findByAge(age.getValue()));
            textField.setValue(String.valueOf(touristService.findByAge(age.getValue()).size()));
        });

        form1.add(all, section, group, sex, year, age, textField, grid1);

        Button allCoaches = new Button("Список тренеров");
        ComboBox<String> sectionCoaches = new ComboBox<>("По секции");
        sectionCoaches.setItems(sectionsService.findNamed());
        sectionCoaches.setAllowCustomValue(false);
        sectionCoaches.setWidthFull();
        IntegerField salaryCoaches = new IntegerField("По зарплате");
        ComboBox<String> sexCoaches = new ComboBox<>("По полу");
        sexCoaches.setItems("ж", "м");
        sexCoaches.setAllowCustomValue(false);
        ComboBox<String> specialization = new ComboBox<>("По специализации");
        specialization.setItems("Пеший", "Конный", "Водный", "Горный", "Другой");
        specialization.setAllowCustomValue(false);
        IntegerField ageCoaches = new IntegerField("По возрасту");
        TextField textField2 = new TextField("Всего результатов");
        textField2.setReadOnly(true);
        Grid<Tourists> grid2 = new Grid<>(Tourists.class);
        grid2.setColumns("name");

        allCoaches.addClickListener(buttonClickEvent -> {
            grid2.setItems(touristService.findAllCoaches());
            textField2.setValue(String.valueOf(touristService.findAllCoaches().size()));
        });
        sectionCoaches.addValueChangeListener(clientValidatedEvent -> {
            grid2.setItems(touristService.findCoachBySection(sectionCoaches.getValue()));
            textField2.setValue(String.valueOf(touristService.findCoachBySection(sectionCoaches.getValue()).size()));
        });
        salaryCoaches.addValueChangeListener(clientValidatedEvent -> {
            grid2.setItems(touristService.findCoachBySalary(salaryCoaches.getValue()));
            textField2.setValue(String.valueOf(touristService.findCoachBySalary(salaryCoaches.getValue()).size()));
        });
        sexCoaches.addValueChangeListener(clientValidatedEvent -> {
            grid2.setItems(touristService.findCoachBySex(sexCoaches.getValue()));
            textField2.setValue(String.valueOf(touristService.findCoachBySex(sexCoaches.getValue()).size()));
        });
        specialization.addValueChangeListener(clientValidatedEvent -> {
            grid2.setItems(touristService.findCoachBySpec(specialization.getValue()));
            textField2.setValue(String.valueOf(touristService.findCoachBySpec(specialization.getValue()).size()));
        });
        ageCoaches.addValueChangeListener(clientValidatedEvent -> {
            grid2.setItems(touristService.findCoachByAge(ageCoaches.getValue()));
            textField2.setValue(String.valueOf(touristService.findCoachByAge(ageCoaches.getValue()).size()));
        });

        form2.add(allCoaches, sectionCoaches, salaryCoaches, sexCoaches, specialization, ageCoaches, textField2, grid2);

        ComboBox<String> sectionCompetitions = new ComboBox<>("По секции");
        sectionCompetitions.setItems(sectionsService.findNamed());
        sectionCompetitions.setAllowCustomValue(false);
        sectionCompetitions.setWidthFull();
        Button allCompetitions = new Button("По всем секциям");
        TextField textField3 = new TextField("Всего результатов");
        textField3.setReadOnly(true);
        Grid<Competitions> grid31 = new Grid<>(Competitions.class);
        Grid<CompetitionSection> grid32 = new Grid<>(CompetitionSection.class);
        grid32.setColumns("name", "section_name");
        grid31.setColumns("name");

        sectionCompetitions.addValueChangeListener(clientValidatedEvent -> {
            form3.remove(grid32);
            form3.add(grid31);
            grid31.setItems(competitionsService.findBySection(sectionCompetitions.getValue()));
            textField3.setValue(String.valueOf(competitionsService.findBySection(sectionCompetitions.getValue()).size()));
        });

        allCompetitions.addClickListener(buttonClickEvent -> {
            form3.remove(grid31);
            form3.add(grid32);
            grid32.setItems(csService.findAllBySection());
            textField3.setValue(String.valueOf(csService.findAllBySection().size()));
        });

        form3.add(allCompetitions, sectionCompetitions, textField3);

        ComboBox<String> sectionFilter5 = new ComboBox<>("По секции");
        sectionFilter5.setItems(sectionsService.findNamed());
        sectionFilter5.setAllowCustomValue(false);
        ComboBox<Integer> groupFilter5 = groupsBox(groupsService);
        Button cancelFilter5 = new Button("Отмена");
        HorizontalLayout q5Layout = new HorizontalLayout(sectionFilter5, groupFilter5, cancelFilter5);
        IntegerField number = new IntegerField("По количеству");
        number.setHelperText("Введите количество походов, в которое ходили туристы");
        number.setEnabled(false);
        ComboBox<Integer> setHike = hikesBox(hikesService);
        setHike.setEnabled(false);
        DatePicker start = new DatePicker("Дата начала");
        start.setHelperText("Выберите период, в который туристы ходили в поход");
        DatePicker end = new DatePicker("Дата конца");
        Button confirm = new Button("ОК");
        HorizontalLayout period = new HorizontalLayout(start, end, confirm);
        period.setEnabled(false);
        ComboBox<String> setRouteq5 = routesBox(routesService);
        setRouteq5.setEnabled(false);
        ComboBox<String> setPoint = pointsBox(pointsService);
        setPoint.setEnabled(false);
        IntegerField setCategory = new IntegerField("По категории");
        setCategory.setStepButtonsVisible(true);
        setCategory.setMin(0);
        setCategory.setMax(4);
        setCategory.setEnabled(false);
        Grid<Tourists> grid5 = new Grid<>(Tourists.class);
        grid5.setColumns("name");
        TextField textField5 = new TextField("Всего результатов");
        textField5.setReadOnly(true);

        sectionFilter5.addValueChangeListener(event -> {
            groupFilter5.setEnabled(false);
            number.setEnabled(true);
            confirm.setEnabled(false);
            end.setEnabled(false);
            setRouteq5.setEnabled(true);
            setHike.setEnabled(true);
            period.setEnabled(true);
            setPoint.setEnabled(true);
            setCategory.setEnabled(true);
        });
        groupFilter5.addValueChangeListener(event -> {
            sectionFilter5.setEnabled(false);
            number.setEnabled(true);
            confirm.setEnabled(false);
            end.setEnabled(false);
            setRouteq5.setEnabled(true);
            setHike.setEnabled(true);
            period.setEnabled(true);
            setPoint.setEnabled(true);
            setCategory.setEnabled(true);
        });
        cancelFilter5.addClickListener(event -> {
            sectionFilter5.setEnabled(true);
            sectionFilter5.clear();
            groupFilter5.setEnabled(true);
            groupFilter5.clear();
            number.setEnabled(false);
            number.clear();
            setRouteq5.setEnabled(false);
            setRouteq5.clear();
            setHike.setEnabled(false);
            setHike.clear();
            period.setEnabled(false);
            setPoint.setEnabled(false);
            setPoint.clear();
            setCategory.setEnabled(false);
            setCategory.clear();
        });

        start.addValueChangeListener(clientValidatedEvent -> {end.setEnabled(true);});
        end.addValueChangeListener(clientValidatedEvent -> {confirm.setEnabled(true);});
        confirm.addClickListener(buttonClickEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecTime(start.getValue(), end.getValue(), sectionFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecTime(start.getValue(), end.getValue(), sectionFilter5.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupTime(start.getValue(), end.getValue(), groupFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupTime(start.getValue(), end.getValue(), groupFilter5.getValue()).size()));
            }
        });

        number.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecAmount(sectionFilter5.getValue(), number.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecAmount(sectionFilter5.getValue(), number.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupAmount(groupFilter5.getValue(), number.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupAmount(groupFilter5.getValue(), number.getValue()).size()));
            }
        });

        setHike.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecHike(sectionFilter5.getValue(), setHike.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecHike(sectionFilter5.getValue(), setHike.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupHike(groupFilter5.getValue(), setHike.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupHike(groupFilter5.getValue(), setHike.getValue()).size()));
            }
        });

        setPoint.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecPoint(setPoint.getValue(), sectionFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecPoint(setPoint.getValue(), sectionFilter5.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupPoint(setPoint.getValue(), groupFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupPoint(setPoint.getValue(), groupFilter5.getValue()).size()));
            }
        });

        setRouteq5.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecRoute(setRouteq5.getValue(), sectionFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecRoute(setRouteq5.getValue(), sectionFilter5.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupRoute(setRouteq5.getValue(), groupFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupRoute(setRouteq5.getValue(), groupFilter5.getValue()).size()));
            }
        });

        setCategory.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter5.isEnabled()) {
                grid5.setItems(touristService.findBySecCat(setCategory.getValue(), sectionFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findBySecCat(setCategory.getValue(), sectionFilter5.getValue()).size()));
            } else if (groupFilter5.isEnabled()) {
                grid5.setItems(touristService.findByGroupCat(setCategory.getValue(), groupFilter5.getValue()));
                textField5.setValue(String.valueOf(touristService.findByGroupCat(setCategory.getValue(), groupFilter5.getValue()).size()));
            }
        });

        form5.add(q5Layout, number, setHike, period, setRouteq5, setPoint, setCategory, textField5, grid5);

        Button allLeaders = new Button("Список лидеров");
        IntegerField salaryLeaders = new IntegerField("По зарплате");
        IntegerField birthYearLeaders = new IntegerField("По году рождения");
        IntegerField empYearLeaders = new IntegerField("По году поступления");
        empYearLeaders.setHelperText("Введите год поступления на работу");
        IntegerField ageLeaders = new IntegerField("По возрасту");
        TextField textField6 = new TextField("Всего результатов");
        textField6.setReadOnly(true);
        Grid<Leaders> grid6 = new Grid<>(Leaders.class);
        grid6.setColumns("name");

        allLeaders.addClickListener(buttonClickEvent -> {
            grid6.setItems(leadersService.findAll());
            textField6.setValue(String.valueOf(leadersService.findAll().size()));
        });
        salaryLeaders.addValueChangeListener(clientValidatedEvent -> {
            grid6.setItems(leadersService.findBySalary(salaryLeaders.getValue()));
            textField6.setValue(String.valueOf(leadersService.findBySalary(salaryLeaders.getValue()).size()));
        });
        birthYearLeaders.addValueChangeListener(clientValidatedEvent -> {
            grid6.setItems(leadersService.findByYear(birthYearLeaders.getValue()));
            textField6.setValue(String.valueOf(leadersService.findByYear(birthYearLeaders.getValue()).size()));
        });
        empYearLeaders.addValueChangeListener(clientValidatedEvent -> {
            grid6.setItems(leadersService.findByEmpYear(empYearLeaders.getValue()));
            textField6.setValue(String.valueOf(leadersService.findByEmpYear(empYearLeaders.getValue()).size()));
        });
        ageLeaders.addValueChangeListener(clientValidatedEvent -> {
            grid6.setItems(leadersService.findByAge(ageLeaders.getValue()));
            textField6.setValue(String.valueOf(leadersService.findByAge(ageLeaders.getValue()).size()));
        });

        form6.add(allLeaders, salaryLeaders, birthYearLeaders, empYearLeaders, ageLeaders, textField6, grid6);

        Button workload = new Button("Нагрузка тренеров");
        ComboBox<String> trainingNames = new ComboBox<>("По занятиям");
        trainingNames.setItems(trainingsService.findNamed());
        trainingNames.setAllowCustomValue(false);
        trainingNames.setWidthFull();
        Label l = new Label();
        Label label = new Label("Общая нагpузка за указанный пеpиод вpемени для данного тpенеpа или секции: ");
        DatePicker startLoad = new DatePicker("Дата начала периода");
        DatePicker endLoad = new DatePicker("Дата конца периода");
        endLoad.setEnabled(false);
        Button cancel7 = new Button("Отмена");
        HorizontalLayout horizontalLayou = new HorizontalLayout(startLoad, endLoad, cancel7);
        ComboBox<String> coachLoad = new ComboBox<>("По тренеру");
        coachLoad.setItems(coachesService.findNamed());
        coachLoad.setAllowCustomValue(false);
        coachLoad.setEnabled(false);
        coachLoad.setWidthFull();
        ComboBox<String> sectionLoad = new ComboBox<>("По секции");
        sectionLoad.setItems(sectionsService.findNamed());
        sectionLoad.setAllowCustomValue(false);
        sectionLoad.setEnabled(false);
        TextField textField7 = new TextField("Всего результатов");
        textField7.setReadOnly(true);
        Grid<Load> grid71 = new Grid<>(Load.class);
        grid71.setColumns("name", "training_name", "hours");
        Grid<Load> grid73 = new Grid<>(Load.class);
        grid73.setColumns("name", "hours");
        Grid<Load> grid74 = new Grid<>(Load.class);
        grid74.setColumns("name", "hours");

        workload.addClickListener(buttonClickEvent -> {
            form7.add(grid71);
            form7.remove(grid73);
            form7.remove(grid74);
            grid71.setItems(loadService.findAllLoads());
            textField7.setValue(String.valueOf(loadService.findAllLoads().size()));
        });
        trainingNames.addValueChangeListener(e -> {
            form7.add(grid71);
            form7.remove(grid73);
            form7.remove(grid74);
            grid71.setItems(loadService.findByTraining(trainingNames.getValue()));
            textField7.setValue(String.valueOf(loadService.findByTraining(trainingNames.getValue()).size()));
        });
        startLoad.addValueChangeListener(e -> {
            endLoad.setEnabled(true);
            endLoad.addValueChangeListener(event -> {
                coachLoad.setEnabled(true);
                sectionLoad.setEnabled(true);
                    coachLoad.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
                        form7.add(grid73);
                        form7.remove(grid71);
                        form7.remove(grid74);
                        grid73.setItems(loadService.findByCoachPeriod(startLoad.getValue(), endLoad.getValue(), coachLoad.getValue()));
                        textField7.setValue(String.valueOf(loadService.findByCoachPeriod(startLoad.getValue(), endLoad.getValue(), coachLoad.getValue()).size()));
                    });
                sectionLoad.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
                    form7.add(grid74);
                    form7.remove(grid73);
                    form7.remove(grid71);
                    grid74.setItems(loadService.findBySecPeriod(startLoad.getValue(), endLoad.getValue(), sectionLoad.getValue()));
                    textField7.setValue(String.valueOf(loadService.findBySecPeriod(startLoad.getValue(), endLoad.getValue(), sectionLoad.getValue()).size()));
                });
            });
        });
        cancel7.addClickListener(buttonClickEvent -> {
            endLoad.setEnabled(false);
            coachLoad.clear();
            coachLoad.setEnabled(false);
            sectionLoad.setEnabled(false);
            sectionLoad.clear();
        });

        form7.add(workload, trainingNames, l, label, horizontalLayou, coachLoad, sectionLoad, textField7);

        ComboBox<String> sectionRoutes = new ComboBox<>("По секции");
        sectionRoutes.setItems(sectionsService.findNamed());
        sectionRoutes.setAllowCustomValue(false);
        DatePicker startRoute = new DatePicker("Дата начала");
        DatePicker endRoute = new DatePicker("Дата конца");
        endRoute.setEnabled(false);
        Button confirmRoute = new Button("ОК");
        confirmRoute.setEnabled(false);
        HorizontalLayout timeRoutes = new HorizontalLayout(startRoute, endRoute, confirmRoute);
        IntegerField counterRoutes = new IntegerField("По количеству групп");
        counterRoutes.setHelperText("Введите количество групп, которое прошло по маршрутам");
        ComboBox<String> instructorRoutes = instructorsBox(touristService);
        instructorRoutes.setWidthFull();
        TextField textField8 = new TextField("Всего результатов");
        textField8.setReadOnly(true);
        Grid<Routes> grid8 = new Grid<>(Routes.class);
        grid8.setColumns("name");

        sectionRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid8.setItems(routesService.findBySection(sectionRoutes.getValue()));
            textField8.setValue(String.valueOf(routesService.findBySection(sectionRoutes.getValue()).size()));
        });
        counterRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid8.setItems(routesService.findByAmount(counterRoutes.getValue()));
            textField8.setValue(String.valueOf(routesService.findByAmount(counterRoutes.getValue()).size()));
        });
        instructorRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid8.setItems(routesService.findByInstructor(instructorRoutes.getValue()));
            textField8.setValue(String.valueOf(routesService.findByInstructor(instructorRoutes.getValue()).size()));
        });
        startRoute.addValueChangeListener(clientValidatedEvent -> {
            endRoute.setEnabled(true);
            endRoute.addValueChangeListener(clientValidatedEvent1 -> {
                confirmRoute.setEnabled(true);
                confirmRoute.addClickListener(buttonClickEvent -> {
                    grid8.setItems(routesService.findByPeriod(startRoute.getValue(), endRoute.getValue()));
                    textField8.setValue(String.valueOf(routesService.findByPeriod(startRoute.getValue(), endRoute.getValue()).size()));
                });
            });
        });

        //form8.add(sectionRoutes, timeRoutes, instructorRoutes, counterRoutes, textField8, grid8);

        IntegerField lengthRoutes = new IntegerField("По длине");
        lengthRoutes.setHelperText("Длина, больше которой должен быть маршрут");
        IntegerField complexityRoutes = new IntegerField("По сложности");
        complexityRoutes.setStepButtonsVisible(true);
        complexityRoutes.setMax(4);
        complexityRoutes.setMin(1);
        ComboBox<String> pointRoutes = pointsBox(pointsService);
        pointRoutes.setWidthFull();
        TextField textField9 = new TextField("Всего результатов");
        textField9.setReadOnly(true);
        Grid<Routes> grid9 = new Grid<>(Routes.class);
        grid9.setColumns("name");

        lengthRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid9.setItems(routesService.findByLength(lengthRoutes.getValue()));
            textField9.setValue(String.valueOf(routesService.findByLength(lengthRoutes.getValue()).size()));
        });
        complexityRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid9.setItems(routesService.findByComplexity(complexityRoutes.getValue()));
            textField9.setValue(String.valueOf(routesService.findByComplexity(complexityRoutes.getValue()).size()));
        });
        pointRoutes.addValueChangeListener(clientValidatedEvent -> {
            grid9.setItems(routesService.findByPoint(pointRoutes.getValue()));
            textField9.setValue(String.valueOf(routesService.findByPoint(pointRoutes.getValue()).size()));
        });

        form9.add(sectionRoutes, timeRoutes, instructorRoutes, counterRoutes, textField8, grid8,
                pointRoutes, lengthRoutes, complexityRoutes, textField9, grid9);

        ComboBox<String> sectionFilter10 = new ComboBox<>("По секции");
        sectionFilter10.setItems(sectionsService.findNamed());
        sectionFilter10.setAllowCustomValue(false);
        ComboBox<Integer> groupFilter10 = groupsBox(groupsService);
        Button cancelFilter10 = new Button("Отмена");
        HorizontalLayout q10Layout = new HorizontalLayout(sectionFilter10, groupFilter10, cancelFilter10);
        ComboBox<String> setRoutes10 = new ComboBox<>("Выберите тип");
        setRoutes10.setItems("Пеший", "Конный", "Водный", "Горный", "Другой");
        setRoutes10.setHelperText("Выберите тип похода, в который могут ходить туристы");
        setRoutes10.setAllowCustomValue(false);
        setRoutes10.setEnabled(false);
        Grid<Tourists> grid10 = new Grid<>(Tourists.class);
        grid10.setColumns("name");
        TextField textField10 = new TextField("Всего результатов");
        textField10.setReadOnly(true);

        sectionFilter10.addValueChangeListener(event -> {
            groupFilter10.setEnabled(false);
            setRoutes10.setEnabled(true);
        });
        groupFilter10.addValueChangeListener(event -> {
            sectionFilter10.setEnabled(false);
            setRoutes10.setEnabled(true);
        });
        cancelFilter10.addClickListener(event -> {
            sectionFilter10.setEnabled(true);
            sectionFilter10.clear();
            groupFilter10.setEnabled(true);
            groupFilter10.clear();
            setRoutes10.setEnabled(false);
            setRoutes10.clear();
        });

        setRoutes10.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter10.isEnabled()) {
                grid10.setItems(touristService.findBySecType(setRoutes10.getValue(), sectionFilter10.getValue()));
                textField10.setValue(String.valueOf(touristService.findBySecType(setRoutes10.getValue(), sectionFilter10.getValue()).size()));
            } else if (groupFilter10.isEnabled()) {
                grid10.setItems(touristService.findByGroupType(setRoutes10.getValue(), groupFilter10.getValue()));
                textField10.setValue(String.valueOf(touristService.findByGroupType(setRoutes10.getValue(), groupFilter10.getValue()).size()));
            }
        });

        form10.add(q10Layout, setRoutes10, textField10, grid10);

        Button instructor = new Button("Инструктор");
        Button instructorSportsman = new Button("Инструктор-спортсмен");
        Button instructorCoach = new Button("Инструктор-тренер");
        Button cancel = new Button("Отмена");
        HorizontalLayout horizontalLayout = new HorizontalLayout(instructor, instructorCoach, instructorSportsman, cancel);
        IntegerField categoryCoach = new IntegerField("По категории");
        categoryCoach.setMin(0);
        categoryCoach.setMax(4);
        categoryCoach.setStepButtonsVisible(true);
        categoryCoach.setEnabled(false);
        IntegerField amountCoach = new IntegerField("По числу походов");
        amountCoach.setEnabled(false);
        ComboBox<Integer> hikeCoaches = new ComboBox<>("По походам");
        hikeCoaches.setItems(hikesService.findIdd());
        hikeCoaches.setAllowCustomValue(false);
        hikeCoaches.setEnabled(false);
        ComboBox<String> routeCoaches = new ComboBox<>("По маршрутам");
        routeCoaches.setItems(routesService.findNamed());
        routeCoaches.setAllowCustomValue(false);
        routeCoaches.setEnabled(false);
        ComboBox<String> pointCoaches = new ComboBox<>("По точкам");
        pointCoaches.setItems(pointsService.findNamed());
        pointCoaches.setAllowCustomValue(false);
        pointCoaches.setEnabled(false);
        TextField textField11 = new TextField("Всего результатов");
        textField11.setReadOnly(true);
        Grid<Tourists> grid11 = new Grid<>(Tourists.class);
        grid11.setColumns("name");

        categoryCoach.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (instructor.isEnabled()) {
                grid11.setItems(touristService.findByInCat(categoryCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCat(categoryCoach.getValue()).size()));
            }
            if (instructorSportsman.isEnabled()) {
                grid11.setItems(touristService.findByInSpCat(categoryCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInSpCat(categoryCoach.getValue()).size()));
            }
            if (instructorCoach.isEnabled()) {
                grid11.setItems(touristService.findByInCoCat(categoryCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCoCat(categoryCoach.getValue()).size()));
            }
        });
        amountCoach.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (instructor.isEnabled()) {
                grid11.setItems(touristService.findByInAmount(amountCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInAmount(amountCoach.getValue()).size()));
            }
            if (instructorSportsman.isEnabled()) {
                grid11.setItems(touristService.findByInSpAmount(amountCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInSpAmount(amountCoach.getValue()).size()));
            }
            if (instructorCoach.isEnabled()) {
                grid11.setItems(touristService.findByInCoAmount(amountCoach.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCoAmount(amountCoach.getValue()).size()));
            }
        });
        hikeCoaches.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (instructor.isEnabled()) {
                grid11.setItems(touristService.findByInHike(hikeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInHike(hikeCoaches.getValue()).size()));
            }
            if (instructorSportsman.isEnabled()) {
                grid11.setItems(touristService.findByInSpHike(hikeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInSpHike(hikeCoaches.getValue()).size()));
            }
            if (instructorCoach.isEnabled()) {
                grid11.setItems(touristService.findByInCoHike(hikeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCoHike(hikeCoaches.getValue()).size()));
            }
        });
        routeCoaches.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (instructor.isEnabled()) {
                grid11.setItems(touristService.findByInRoute(routeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInRoute(routeCoaches.getValue()).size()));
            }
            if (instructorSportsman.isEnabled()) {
                grid11.setItems(touristService.findByInSpRoute(routeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInSpRoute(routeCoaches.getValue()).size()));
            }
            if (instructorCoach.isEnabled()) {
                grid11.setItems(touristService.findByInCoRoute(routeCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCoRoute(routeCoaches.getValue()).size()));
            }
        });
        pointCoaches.addValueChangeListener(integerFieldIntegerComponentValueChangeEvent -> {
            if (instructor.isEnabled()) {
                grid11.setItems(touristService.findByInPoint(pointCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInPoint(pointCoaches.getValue()).size()));
            }
            if (instructorSportsman.isEnabled()) {
                grid11.setItems(touristService.findByInSpPoint(pointCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInSpPoint(pointCoaches.getValue()).size()));
            }
            if (instructorCoach.isEnabled()) {
                grid11.setItems(touristService.findByInCoPoint(pointCoaches.getValue()));
                textField11.setValue(String.valueOf(touristService.findByInCoPoint(pointCoaches.getValue()).size()));
            }
        });

        instructor.addClickListener(event -> {
            instructorCoach.setEnabled(false);
            instructorSportsman.setEnabled(false);
            categoryCoach.setEnabled(true);
            amountCoach.setEnabled(true);
            hikeCoaches.setEnabled(true);
            routeCoaches.setEnabled(true);
            pointCoaches.setEnabled(true);
        });
        instructorCoach.addClickListener(event -> {
            instructor.setEnabled(false);
            instructorSportsman.setEnabled(false);
            categoryCoach.setEnabled(true);
            amountCoach.setEnabled(true);
            hikeCoaches.setEnabled(true);
            routeCoaches.setEnabled(true);
            pointCoaches.setEnabled(true);
        });
        instructorSportsman.addClickListener(event -> {
            instructorCoach.setEnabled(false);
            instructor.setEnabled(false);
            categoryCoach.setEnabled(true);
            amountCoach.setEnabled(true);
            hikeCoaches.setEnabled(true);
            routeCoaches.setEnabled(true);
            pointCoaches.setEnabled(true);
        });
        cancel.addClickListener(event -> {
            instructorCoach.setEnabled(true);
            instructor.setEnabled(true);
            instructorSportsman.setEnabled(true);
            categoryCoach.setEnabled(false);
            categoryCoach.clear();
            amountCoach.setEnabled(false);
            amountCoach.clear();
            hikeCoaches.setEnabled(false);
            hikeCoaches.clear();
            routeCoaches.setEnabled(false);
            routeCoaches.clear();
            pointCoaches.setEnabled(false);
            pointCoaches.clear();
        });

        form11.add(horizontalLayout, categoryCoach, amountCoach, hikeCoaches, routeCoaches, pointCoaches, textField11, grid11);

        ComboBox<String> sectionWCoach = new ComboBox<>("По секции");
        sectionWCoach.setItems(sectionsService.findNamed());
        sectionWCoach.setAllowCustomValue(false);
        sectionWCoach.setHelperText("Введите секцию");
        ComboBox<Integer> groupWCoach = groupsBox(groupsService);
        groupWCoach.setHelperText("Введите группу");
        TextField textField12 = new TextField("Всего результатов");
        textField12.setReadOnly(true);
        Grid<Tourists> grid12 = new Grid<>(Tourists.class);
        grid12.setColumns("name");

        sectionWCoach.addValueChangeListener(clientValidatedEvent -> {
            grid12.setItems(touristService.findBySecOwnCoach(sectionWCoach.getValue()));
            textField12.setValue(String.valueOf(touristService.findBySecOwnCoach(sectionWCoach.getValue()).size()));
        });
        groupWCoach.addValueChangeListener(clientValidatedEvent -> {
            grid12.setItems(touristService.findByGroupOwnCoach(groupWCoach.getValue()));
            textField12.setValue(String.valueOf(touristService.findByGroupOwnCoach(groupWCoach.getValue()).size()));
        });

        form12.add(sectionWCoach, groupWCoach, textField12, grid12);

        ComboBox<String> sectionFilter = new ComboBox<>("По секции");
        sectionFilter.setItems(sectionsService.findNamed());
        sectionFilter.setAllowCustomValue(false);
        ComboBox<Integer> groupFilter = groupsBox(groupsService);
        Button cancelFilter = new Button("Отмена");
        HorizontalLayout q13Layout = new HorizontalLayout(sectionFilter, groupFilter, cancelFilter);
        Button allRoutes = new Button("По всем маршрутам");
        allRoutes.setEnabled(false);
        MultiSelectComboBox<String> setRoutes = new MultiSelectComboBox<>("По маршрутам");
        setRoutes.setItems(routesService.findNamed());
        /*MultiSelectComboBox<Integer> setRoutes = new MultiSelectComboBox<>("По маршрутам");
        setRoutes.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);*/
        setRoutes.setEnabled(false);
        TextField textField13 = new TextField("Всего результатов");
        textField13.setReadOnly(true);
        Grid<Tourists> grid13 = new Grid<>(Tourists.class);
        grid13.setColumns("name");

        allRoutes.addClickListener(buttonClickEvent -> {
            if (sectionFilter.isEnabled()) {
                grid13.setItems(touristService.findBySecAllR(sectionFilter.getValue()));
                textField13.setValue(String.valueOf(touristService.findBySecAllR(sectionFilter.getValue()).size()));
            } else if (groupFilter.isEnabled()) {
                grid13.setItems(touristService.findByGrAllR(groupFilter.getValue()));
                textField13.setValue(String.valueOf(touristService.findByGrAllR(groupFilter.getValue()).size()));
            }
        });
        setRoutes.addValueChangeListener(clientValidatedEvent -> {
            if (sectionFilter.isEnabled()) {
                grid13.setItems(touristService.findBySecR13(sectionFilter.getValue(), setRoutes.getValue().toArray(new String[0]), setRoutes.getValue().size()));
                textField13.setValue(String.valueOf(touristService.findBySecR13(sectionFilter.getValue(), setRoutes.getValue().toArray(new String[0]), setRoutes.getValue().size()).size()));
            } else if (groupFilter.isEnabled()) {
                grid13.setItems(touristService.findByGR13(groupFilter.getValue(), setRoutes.getValue().toArray(new String[0]), setRoutes.getValue().size()));
                textField13.setValue(String.valueOf(touristService.findByGR13(groupFilter.getValue(), setRoutes.getValue().toArray(new String[0]), setRoutes.getValue().size()).size()));
            }
        });
        sectionFilter.addValueChangeListener(event -> {
            groupFilter.setEnabled(false);
            allRoutes.setEnabled(true);
            setRoutes.setEnabled(true);
        });
        groupFilter.addValueChangeListener(event -> {
            sectionFilter.setEnabled(false);
            allRoutes.setEnabled(true);
            setRoutes.setEnabled(true);
        });
        cancelFilter.addClickListener(event -> {
            sectionFilter.setEnabled(true);
            sectionFilter.clear();
            groupFilter.setEnabled(true);
            groupFilter.clear();
            allRoutes.setEnabled(false);
            setRoutes.setEnabled(false);
            setRoutes.clear();
        });

        form13.add(q13Layout, allRoutes, setRoutes, textField13, grid13);

        add(createFormLayout());

        add(accordion);
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        return formLayout;
    }

    private ComboBox<Integer> hikesBox(HikesService hikesService) {
        ComboBox<Integer> hikeCoaches = new ComboBox<>("По походам");
        hikeCoaches.setItems(hikesService.findIdd());
        hikeCoaches.setAllowCustomValue(false);
        return hikeCoaches;
    }

    private ComboBox<String> routesBox(RoutesService routesService) {
        ComboBox<String> routeCoaches = new ComboBox<>("По маршрутам");
        routeCoaches.setItems(routesService.findNamed());
        routeCoaches.setAllowCustomValue(false);
        return routeCoaches;
    }

    private ComboBox<String> pointsBox(PointsService pointsService) {
        ComboBox<String> pointCoaches = new ComboBox<>("По точкам");
        pointCoaches.setItems(pointsService.findNamed());
        pointCoaches.setAllowCustomValue(false);
        return pointCoaches;
    }

    private ComboBox<Integer> groupsBox(GroupsService groupsService) {
        ComboBox<Integer> hikeCoaches = new ComboBox<>("По группе");
        hikeCoaches.setItems(groupsService.findIdd());
        hikeCoaches.setAllowCustomValue(false);
        return hikeCoaches;
    }

    private ComboBox<String> instructorsBox(TouristService touristService) {
        ComboBox<String> pointCoaches = new ComboBox<>("По инструктору");
        pointCoaches.setItems(touristService.findNamedInstructors());
        pointCoaches.setAllowCustomValue(false);
        return pointCoaches;
    }
}
