import React, { createContext, useContext, useState, useCallback } from 'react';
import axios from 'axios';

const ProjectContext = createContext();

// eslint-disable-next-line react/prop-types
export const ProjectProvider = ({ children }) => {
  const [projects, setProjects] = useState([]);
  const [selectedProjectId, setSelectedProjectId] = useState(null);
  const [sprints, setSprints] = useState([]);
  const [members, setMembers] = useState([]); // 멤버 데이터를 저장할 상태 추가

  const fetchSprints = async (projectId) => {
    if (!projectId) {
      console.warn('프로젝트 ID가 없습니다.');
      return;
    }

    try {
      const response = await axios.get(
        `https://api.agilementor.kr/api/projects/${projectId}/sprints`,
        {
          headers: {
            Cookie: document.cookie,
          },
          withCredentials: true,
        },
      );
      setSprints(response.data);
    } catch (error) {
      console.error('스프린트 데이터를 가져오는 중 오류 발생:', error);
    }
  };

  const fetchMembers = useCallback(async (projectId) => {
    if (!projectId) {
      console.warn('프로젝트 ID가 없습니다.');
      setMembers([]); // 프로젝트 선택 해제 시 멤버 초기화
      return;
    }

    try {
      const response = await axios.get(
        `https://api.agilementor.kr/api/projects/${projectId}/members`,
        {
          headers: {
            Cookie: document.cookie,
          },
          withCredentials: true,
        },
      );
      setMembers(response.data); // 멤버 데이터를 상태에 저장
    } catch (error) {
      console.error('멤버 데이터를 가져오는 중 오류 발생:', error);
    }
  }, []); // useCallback으로 메모이제이션

  return (
    <ProjectContext.Provider
      // eslint-disable-next-line react/jsx-no-constructed-context-values
      value={{
        projects,
        setProjects,
        selectedProjectId,
        setSelectedProjectId,
        sprints,
        setSprints,
        fetchSprints,
        members,
        fetchMembers,
      }}
    >
      {children}
    </ProjectContext.Provider>
  );
};

export const useProjects = () => {
  const context = useContext(ProjectContext);
  if (!context) {
    throw new Error('useProjects must be used within a ProjectProvider');
  }
  return context;
};
