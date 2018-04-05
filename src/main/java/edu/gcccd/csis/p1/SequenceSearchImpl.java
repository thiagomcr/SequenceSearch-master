package edu.gcccd.csis.p1;

public class SequenceSearchImpl extends SequenceSearch {

    public SequenceSearchImpl(final String content, final String start, final String end) {
        super(content, start, end);
    }

    @Override
    public String[] getAllTaggedSequences() {
        int indexStart = content.indexOf(startTag);
        int indexEnd = content.indexOf(endTag, indexStart+startTag.length());;
        int howManyStartTags =  howManyTimes(content, startTag);

        String[] taggedSequences = null;

        if(startTag.equals(endTag)){
            taggedSequences = new String[howManyStartTags/2];
        }else{
            taggedSequences = new String[howManyStartTags];
        }

        if(indexStart != -1 && indexEnd != -1){
            int counter = 0;
            while(indexStart != -1){
                taggedSequences[counter++] = content.substring(indexStart + startTag.length(), indexEnd);
                indexStart = content.indexOf(startTag, indexEnd + endTag.length());
                indexEnd = content.indexOf(endTag, indexStart + startTag.length());
            }
        }

        return taggedSequences;
    }

    int howManyTimes(final String s, final String t){
        int counter = 0;
        int fromIndex = -t.length();
        do {
            fromIndex = s.indexOf(t, fromIndex+t.length());
            if(fromIndex != -1){
                counter++;
            }

        } while(0 <= fromIndex);
        return counter;
    }

    @Override
    public String getLongestTaggedSequence() {

        String[] allSequences = getAllTaggedSequences();
        int longestLength=0;
        String longestString = null;
        for(int i=0; i<allSequences.length; i++){
            if(allSequences[i].length() >= longestLength){
                longestLength = allSequences[i].length();
                longestString = allSequences[i];
            }
        }

        return  longestString;
    }

    @Override
    public String displayStringArray() {
        String[] allSequences = getAllTaggedSequences();

        String formattedString = "";
        for(int i=0; i < allSequences.length; i++){
            formattedString += allSequences[i]+" : "+allSequences[i].length()+"\n";
        }
        return formattedString;
    }

    @Override
    public String toString() {
        String temp = content.replaceAll(startTag, "");
        temp = temp.replaceAll(endTag, "");

        return temp;
    }

}