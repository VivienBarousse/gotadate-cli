/*
 * This file is part of gotadate.
 *
 * gotadate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * gotadate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with gotadate.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aperigeek.gotadate.cli;

import com.aperigeek.gotadate.parser.DateParseException;
import com.aperigeek.gotadate.parser.DateParser;
import com.aperigeek.gotadate.token.DateTokenizer;
import com.aperigeek.gotadate.token.TokenizerException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vivien Barousse
 */
public class Main {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: ");
            System.err.println("\tjava -jar gotadate-cli.jar file");
            System.err.println("\tWhere:");
            System.err.println("\t\tfile is the file to analyse");
            System.exit(1);
        }
        
        try {
            Reader reader = new FileReader(args[0]);
            DateTokenizer tokenizer = new DateTokenizer(reader);
            DateParser parser = new DateParser(tokenizer);
            parser.parse();
            
            List<Date> parsed = parser.getParsed();
            
            System.out.println(parsed.size() + " dates found:");
            for (Date date : parsed) {
                System.out.println(date.toString());
            }
        } catch (DateParseException ex) {
            System.err.println("Error during parsing");
            ex.printStackTrace(System.err);
            System.exit(4);
        } catch (TokenizerException ex) {
            System.err.println("Error during tokenization");
            ex.printStackTrace(System.err);
            System.exit(3);
        } catch (IOException ex) {
            System.err.println("Unable to read file");
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
    
}
