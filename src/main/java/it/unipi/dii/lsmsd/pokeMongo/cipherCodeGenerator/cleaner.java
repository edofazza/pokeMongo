package it.unipi.dii.lsmsd.pokeMongo.cipherCodeGenerator;

import java.io.*;

public class cleaner {
    public static void main(String[] args) {
        for (int i = 0; i < 77; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("cipherCodeRepo/cipherFriends/cipherUser"+ i + ".txt"), "utf-8"))) {
                writer.write("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 77; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("cipherCodeRepo/cipherFavorite/cipherUser"+ i + ".txt"), "utf-8"))) {
                writer.write("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 77; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("cipherCodeRepo/cipherTeam/cipherUser"+ i + ".txt"), "utf-8"))) {
                writer.write("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 77; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("cipherCodeRepo/cipherPost/cipherUser"+ i + ".txt"), "utf-8"))) {
                writer.write("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 29; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("cipherCodeRepo/cipherUserCode/cipherUser"+ i + ".txt"), "utf-8"))) {
                writer.write("");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
