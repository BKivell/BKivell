This project has two modes, “Unsynchronized mode” and “Synchronized mode”. The ships 
may crash under the “Unsynchronized mode” because of race conditions. It shall run safely 
under the “Synchronized mode”. 

• “Unsynchronized mode” 
o If one ship moves to the island, all other ships try to wait until the ship finishes 
the job (Allows race conditions happen). 
o Ships may crash if there are more than one ship move to island at a time. 
o The program detects and prints message on the GUI when ships crash. 

• “Synchronized mode” 
o Only one ship moves at a time. (NO race conditions) 