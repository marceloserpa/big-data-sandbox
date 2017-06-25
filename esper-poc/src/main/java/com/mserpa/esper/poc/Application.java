package com.mserpa.esper.poc;

public class Application {

    public static void main(String[] args) {

        EsperEngine esperEngine = new EsperEngine();

        // Creating basic components of PC001
        esperEngine.registerDefaultEvent("PC001CPU");
        esperEngine.registerDefaultEvent("PC001MEMORY");

        // Creating rules to identify if PC001 is working
        esperEngine.addEPL("create window PC001Status.win:length(1) as (name string, status string, parent string)");
        esperEngine.addEPL("insert into PC001Status select cpu.parent as name, 'error' as status, 'LABRoom14' as parent from PC001CPUStatus as cpu, PC001MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC001' and (cpu.status = 'error' or mem.status = 'error')");
        esperEngine.addEPL("insert into PC001Status select cpu.parent as name, 'warning' as status, 'LABRoom14' as parent from PC001CPUStatus as cpu, PC001MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC001' and (cpu.status = 'warning' or mem.status = 'warning')");
        esperEngine.addEPL("insert into PC001Status select cpu.parent as name,'good' as status, 'LABRoom14' as parent from PC001CPUStatus as cpu, PC001MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC001' and cpu.status = 'good' and mem.status = 'good'");

        // Creating basic components of PC002
        esperEngine.registerDefaultEvent("PC002CPU");
        esperEngine.registerDefaultEvent("PC002MEMORY");

        // Creating rules to identify if PC002 is working
        esperEngine.addEPL("create window PC002Status.win:length(1) as (name string, status string, parent string)");
        esperEngine.addEPL("insert into PC002Status select cpu.parent as name, 'error' as status, 'LABRoom14' as parent from PC002CPUStatus as cpu, PC002MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC002' and (cpu.status = 'error' or mem.status = 'error')");
        esperEngine.addEPL("insert into PC002Status select cpu.parent as name, 'warning' as status, 'LABRoom14' as parent from PC002CPUStatus as cpu, PC002MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC002' and (cpu.status = 'warning' or mem.status = 'warning')");
        esperEngine.addEPL("insert into PC002Status select cpu.parent as name,'good' as status, 'LABRoom14' as parent from PC002CPUStatus as cpu, PC002MEMORYStatus as mem where cpu.parent = mem.parent and cpu.parent = 'PC002' and cpu.status = 'good' and mem.status = 'good'");

        // Creating a group view to identify all machine inside a laboratory
        esperEngine.addEPL("create window LABRoom14Status.win:length(1) as (name string, status string, parent string)");
        esperEngine.addEPL("insert into LABRoom14Status select pc1.parent as name, 'good' as status, 'none' as parent from PC001Status as pc1, PC002Status as pc2 where pc1.parent = pc2.parent and pc1.parent = 'LABRoom14' and (pc1.status = 'good' and pc2.status = 'good')");
        esperEngine.addEPL("insert into LABRoom14Status select pc1.parent as name, 'error' as status, 'none' as parent from PC001Status as pc1, PC002Status as pc2 where pc1.parent = pc2.parent and pc1.parent = 'LABRoom14' and (pc1.status = 'error' or pc2.status = 'error')");
        esperEngine.addEPL("insert into LABRoom14Status select pc1.parent as name, 'warning' as status, 'none' as parent from PC001Status as pc1, PC002Status as pc2 where pc1.parent = pc2.parent and pc1.parent = 'LABRoom14' and (pc1.status = 'warning' or pc2.status = 'warning')");

        esperEngine.triggerEvent(new Event("PC001CPU", "up", "PC001"));
        esperEngine.triggerEvent(new Event("PC001MEMORY", "warning", "PC001"));
        esperEngine.triggerEvent(new Event("PC002CPU", "up", "PC002"));
        esperEngine.triggerEvent(new Event("PC002MEMORY", "up", "PC002"));


    }

}














