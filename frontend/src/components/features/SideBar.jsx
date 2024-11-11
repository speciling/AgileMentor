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
import { HEADER_HEIGHT } from '@components/features/Header';
// eslint-disable-next-line import/no-unresolved
import LogoutButton from '@components/common/LogoutButton';
// eslint-disable-next-line import/no-unresolved
import Member from '@components/common/Member';

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
  width: 23vw;
  max-width: 400px;
  min-width: 250px;
  min-height: calc(100vh - ${HEADER_HEIGHT});
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
`;

const CreateProjectButtonWrapper = styled.div`
  margin-top: 20px;
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
  margin-top: 15px;
  margin-left: 20px;
  color: #5f8f86;
  font-size: 24px;
  text-decoration: none;
  cursor: pointer;

  &:hover {
    color: #2a7a7a;
  }
`;

const IoHomeIcon = styled(IoHome)`
  margin-right: 8px;
  font-size: 24px;
  color: #5f8f86;
`;

const NavigateMenuWrapper = styled.div`
  width: 100%;
  padding-left: 20px;
`;

const MemberWrapper = styled.div`
  width: 100%;
  padding-left: 20px;
  margin-bottom: 25px;
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
  height: 2px;
  background-color: #eaeaea;
  margin-bottom: 25px;
`;

const LogoutButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  padding-left: 20px;
  margin-top: auto;
`;
