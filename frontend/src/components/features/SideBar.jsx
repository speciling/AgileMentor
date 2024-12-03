import React, { useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { IoHome } from 'react-icons/io5';
// eslint-disable-next-line import/no-unresolved
import CreateProjectButton from '@components/common/NewProject/index';
// eslint-disable-next-line import/no-unresolved
import SelectProject from '@components/common/SelectProject';
// eslint-disable-next-line import/no-unresolved
import NavigateMenu from '@components/common/NavigateMenu';
// eslint-disable-next-line import/no-unresolved
import LogoutButton from '@components/common/LogoutButton';
// eslint-disable-next-line import/no-unresolved
import Member from '@components/common/Member';
// eslint-disable-next-line import/no-unresolved
import SettingButton from '@components/common/SettingButton';
import { useNavigate } from 'react-router-dom';
import { useProjects } from '../../provider/projectContext';

const SideBar = () => {
  const {
    setProjects,
    projects,
    selectedProjectId,
    setSelectedProjectId,
    members,
    fetchMembers,
  } = useProjects();
  const navigate = useNavigate();

  // Fetch projects on component mount
  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await axios.get(
          'https://api.agilementor.kr/api/projects',
          {
            withCredentials: true,
          },
        );
        setProjects(response.data);
      } catch (error) {
        console.error('Error fetching projects:', error);
        alert('프로젝트 목록을 가져오는 중 오류가 발생했습니다.');
      }
    };

    fetchProjects();
  }, [setProjects]);

  // Fetch members when selectedProjectId changes
  useEffect(() => {
    if (selectedProjectId) {
      fetchMembers(selectedProjectId);
    } else {
      // Reset members when no project is selected
      fetchMembers(null);
    }
  }, [selectedProjectId, fetchMembers]);

  return (
    <SidebarContainer>
      <CreateProjectButtonWrapper>
        <CreateProjectButton />
      </CreateProjectButtonWrapper>
      <SelectProjectWrapper>
        <SelectProject
          projects={projects}
          onSelectProject={(projectId) => setSelectedProjectId(projectId)}
        />
      </SelectProjectWrapper>
      <DashboardLink onClick={() => navigate('/dashboard')}>
        <IoHomeIcon />
        대시보드
      </DashboardLink>
      <NavigateMenuWrapper>
        <NavigateMenu />
      </NavigateMenuWrapper>
      {selectedProjectId && (
        <MemberWrapper>
          <Member members={members} />
        </MemberWrapper>
      )}
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
