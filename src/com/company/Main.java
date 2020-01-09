package com.company;

import com.company.Models.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Map<Integer,Member> members = new HashMap();
    private Map<Integer,Survey> surveys = new HashMap();
    private List<Participation> participations = new ArrayList();

    public static void main(String[] args) {
        String membersPath = "D:/redynatainterj/OO - 2 - Members.csv";
        String surveysPath = "D:/redynatainterj/OO - 2 - Surveys.csv";
        String participationsPath = "D:/redynatainterj/OO - 2 - Participation.csv";

        Main program = new Main();

        program.participations = program.reader(participationsPath,Participation.class);
        program.members = program.reader(membersPath, Member.class).stream().collect(Collectors.toMap(m -> m.getMemberID(), m -> m));
        program.surveys = program.reader(surveysPath, Survey.class).stream().collect(Collectors.toMap(s -> s.getSurveyID(), s -> s));

        /*for (Survey s : program.filledSurveys(program.members.get(1), program.surveys, program.participations))
            System.out.println(s.getName());

        System.out.println(program.collectedPoints(program.members.get(1), program.surveys, program.participations));

        for (Member m : program.surveyFillers(program.surveys.get(16), program.members, program.participations)){
            System.out.println(m.getName());
        }

        System.out.println(program.canBeCalledMembers(program.surveys.get(16), program.members, program.participations).size());

        program.showStatistics(program.surveys.values().stream().collect(Collectors.toList()), program.participations);*/
    }

    public <T> List<T> reader(String path, Class<T> tClass){
        List<T> list = new ArrayList<>();
        String line;
        BufferedReader bufferedReader = null;

        try{
            bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                String[] elements = line.split(",");

                if (tClass.isInstance(new Member())){
                    Member newMember = new Member(elements);
                    list.add(tClass.cast(newMember));
                } else if (tClass.isInstance(new Participation())){
                    Participation newParticipation = new Participation(elements);
                    list.add(tClass.cast(newParticipation));
                } else if (tClass.isInstance(new Survey())){
                    Survey newSurvey = new Survey(elements);
                    list.add(tClass.cast(newSurvey));
                }
            }
        }catch (FileNotFoundException e){}
         catch (IOException e){}
         finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e) {}
            }
        }

        return list;
    }

    public List<Member> surveyFillers(Survey survey, Map<Integer, Member> members, List<Participation> participationList){
        List<Member> fillers = new ArrayList<>();

        for(Participation p : participationList.stream()
                                .filter(p -> p.getSurveyID() == survey.getSurveyID())
                                .filter(p -> p.getStatus() == STATUS.COMPLETED)
                                .collect(Collectors.toList())){
                fillers.add(members.get(p.getMemberID()));
        }

        return fillers;
    }

    public List<Survey> filledSurveys(Member member, Map<Integer, Survey> surveys, List<Participation> participationList, STATUS... status){
        List<Survey> filled = new ArrayList<>();

        for (Participation p : participationList.stream()
                                .filter(p -> p.getMemberID() == member.getMemberID())
                                .filter(p -> p.getStatus() == (status.length >= 1 ? status[0] : STATUS.COMPLETED))
                                .collect(Collectors.toList())){
            filled.add(surveys.get(p.getSurveyID()));
        }

        return filled;
    }

    public int collectedPoints(Member member, Map<Integer, Survey> surveys, List<Participation> participationList){
        int points = 0;

        for (Survey s : filledSurveys(member, surveys, participationList, STATUS.COMPLETED))
            points += s.getCompletionPoint();

        for (Survey s : filledSurveys(member, surveys, participationList, STATUS.FILTERED))
            points += s.getFilteredPoint();

        return points;
    }

    public List<Member> canBeCalledMembers(Survey survey, Map<Integer, Member> members, List<Participation> participationList){
        List<Member> persons = new ArrayList(members.values().stream().filter(m -> m.isActive()).collect(Collectors.toList()));

        for (Member m : surveyFillers(survey, members, participationList)){
            persons.remove(m);
        }

        return persons;
    }

    public void showStatistics(List<Survey> surveys, List<Participation> participations){
        List<Statistics> statisticsList = new ArrayList<>();

        for (Survey s : surveys){
            Statistics stat = new Statistics();
            double time = 0;

            stat.setSurveyID(s.getSurveyID());
            stat.setSurveyName(s.getName());
            stat.setNumberOfFillers(participations.stream()
                                    .filter(p -> p.getSurveyID() == s.getSurveyID())
                                    .filter(p -> p.getStatus() == STATUS.COMPLETED)
                                    .collect(Collectors.toList())
                                    .size());
            stat.setNumberOfFiltered(participations.stream()
                                    .filter(p -> p.getSurveyID() == s.getSurveyID())
                                    .filter(p -> p.getStatus() == STATUS.FILTERED)
                                    .collect(Collectors.toList())
                                    .size());
            stat.setNumberOfRejectors(participations.stream()
                                    .filter(p -> p.getSurveyID() == s.getSurveyID())
                                    .filter(p -> p.getStatus() == STATUS.REJECTED)
                                    .collect(Collectors.toList())
                                    .size());

            for (Participation p : participations.stream()
                                    .filter(p -> p.getSurveyID() == s.getSurveyID())
                                    .filter(p -> p.getStatus() == STATUS.COMPLETED)
                                    .collect(Collectors.toList())){
                time += p.getLength();
            }

            if (stat.getNumberOfFillers() > 0)
                stat.setAverageFillTime(time / stat.getNumberOfFillers());
            else
                stat.setAverageFillTime(0);

            statisticsList.add(stat);
        }

        for (Statistics s : statisticsList){
            System.out.println(s.toString());
        }
    }

}
