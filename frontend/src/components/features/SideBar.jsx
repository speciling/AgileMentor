import React from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import CreateProjectButton from '@components/common/NewProject/index';
// eslint-disable-next-line import/no-unresolved
import SelectProject from '@components/common/SelectProject';
import { IoHome } from 'react-icons/io5';
// eslint-disable-next-line import/no-unresolved
import NavigateMenu from '@components/common/NavigateMenu';
// eslint-disable-next-line import/no-unresolved
import LogoutButton from '@components/common/LogoutButton';
// eslint-disable-next-line import/no-unresolved
import Member from '@components/common/Member';

// eslint-disable-next-line import/no-unresolved
import SettingButton from '@components/common/SettingButton';
// eslint-disable-next-line import/no-unresolved

const SideBar = () => {
  const members = [
    { id: 1, name: '지연우', isAdmin: true },
    { id: 2, name: '오 탐', isAdmin: false },
    { id: 3, name: '장현지', isAdmin: false },
    { id: 4, name: '임지환', isAdmin: false },
  ];

  return (
    <SidebarContainer>
      <CreateProjectButtonWrapper>
        <CreateProjectButton />
      </CreateProjectButtonWrapper>
      <SelectProjectWrapper>
        <SelectProject />
      </SelectProjectWrapper>
      <DashboardLink>
        <IoHomeIcon />
        대시보드
      </DashboardLink>
      <NavigateMenuWrapper>
        <NavigateMenu />
      </NavigateMenuWrapper>
      <MemberWrapper>
        <Member members={members} />
      </MemberWrapper>
      <DividerWrapper>
        <SettingButtonWrapper>
          <SettingButton onClick={() => console.log('클릭됨')} />
        </SettingButtonWrapper>
        <Divider />
        <LogoutButtonWrapper>
          <LogoutButton />
        </LogoutButtonWrapper>
      </DividerWrapper>
    </SidebarContainer>
  );
};

export default SideBar;

const SidebarContainer = styled.div`
  background-color: #fff;
  width: 18vw;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2vh;
`;

const CreateProjectButtonWrapper = styled.div`
  margin-top: 2vh;
  width: 100%;
  display: flex;
  justify-content: center;
`;

const SelectProjectWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

const DashboardLink = styled.a`
  display: flex;
  align-items: center;
  align-self: flex-start;
  margin-top: 1.5vh;
  margin-left: 2vw;
  color: #5f8f86;
  font-size: 2.4vh;
  text-decoration: none;
  cursor: pointer;

  &:hover {
    color: #2a7a7a;
  }
`;

const IoHomeIcon = styled(IoHome)`
  margin-right: 0.8vw;
  font-size: 2.4vh;
  color: #5f8f86;
`;

const NavigateMenuWrapper = styled.div`
  width: 100%;
  padding-left: 2vw;
`;

const MemberWrapper = styled.div`
  width: 100%;
  padding-left: 2vw;
`;

const DividerWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0;
`;

const Divider = styled.div`
  width: 100%;
  height: 0.2vh;
  background-color: #eaeaea;
  margin-bottom: 2vh;
`;

const LogoutButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  padding-left: 2vw;
`;

const SettingButtonWrapper = styled.div`
  width: 50%;
  display: flex;
  justify-content: center;
  margin-bottom: 2vh;
`;
