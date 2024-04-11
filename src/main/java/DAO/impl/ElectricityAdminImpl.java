package DAO.impl;

import java.util.List;

import model.ElectricityAdminModel;

public interface ElectricityAdminImpl {
        public int addElectricityAdmin(ElectricityAdminModel admin) throws Exception;

        public List<ElectricityAdminModel> getElectricityAdmins() throws Exception;
}
